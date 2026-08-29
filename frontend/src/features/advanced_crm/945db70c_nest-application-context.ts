// @ts-nocheck
import {
  type INestApplicationContext,
  Logger,
  type LoggerService,
  type LogLevel,
  ShutdownSignal,
} from '@nestjs/common';
import { iterate } from 'iterare';
import { MESSAGES } from './constants.js';
import { UnknownModuleException } from './errors/exceptions/index.js';
import { createContextId } from './helpers/context-id-factory.js';
import {
  callAppShutdownHook,
  callBeforeAppShutdownHook,
  callModuleBootstrapHook,
  callModuleDestroyHook,
  callModuleInitHook,
} from './hooks/index.js';
import { AbstractInstanceResolver } from './injector/abstract-instance-resolver.js';
import { ModuleCompiler } from './injector/compiler.js';
import { NestContainer } from './injector/container.js';
import { Injector } from './injector/injector.js';
import { InstanceLinksHost } from './injector/instance-links-host.js';
import { ContextId } from './injector/instance-wrapper.js';
import { Module } from './injector/module.js';
import type { Abstract, DynamicModule, Type } from '@nestjs/common';
import {
  type GetOrResolveOptions,
  type SelectOptions,
  type ShutdownHooksOptions,
  type NestApplicationContextOptions,
  isEmptyArray,
} from '@nestjs/common/internal';

export class NestApplicationContext<
  TOptions extends NestApplicationContextOptions =
    NestApplicationContextOptions,
>
  extends AbstractInstanceResolver
  implements INestApplicationContext
{
  protected isInitialized = false;
  protected injector: Injector;
  protected readonly logger = new Logger(NestApplicationContext.name, {
    timestamp: true,
  });

  private shouldFlushLogsOnOverride = false;
  private readonly activeShutdownSignals = new Array<string>();
  private readonly moduleCompiler: ModuleCompiler;
  private shutdownCleanupRef?: (...args: unknown[]) => unknown;
  private _instanceLinksHost: InstanceLinksHost;
  private _moduleRefsForHooksByDistance?: Array<Module>;
  private initializationPromise?: Promise<void>;

  protected get instanceLinksHost() {
    if (!this._instanceLinksHost) {
      this._instanceLinksHost = new InstanceLinksHost(this.container);
    }
    return this._instanceLinksHost;
  }

  constructor(
    protected readonly container: NestContainer,
    protected readonly appOptions: TOptions = {} as TOptions,
    private contextModule: Module | null = null,
    private readonly scope = new Array<Type<any>>(),
  ) {
    super();
    this.injector = new Injector();
    this.moduleCompiler = container.getModuleCompiler();

    if (this.appOptions.preview) {
      this.printInPreviewModeWarning();
    }
  }

  public selectContextModule() {
    const modules = this.container.getModules().values();
    this.contextModule = modules.next().value!;
  }

  public select<T>(
    moduleType: Type<T> | DynamicModule,
    selectOptions?: SelectOptions,
  ): INestApplicationContext {
    const modulesContainer = this.container.getModules();
    const contextModuleCtor = this.contextModule!.metatype;
    const scope = this.scope.concat(contextModuleCtor);

    const moduleTokenFactory = this.container.getModuleTokenFactory();
    const { type, dynamicMetadata } =
      this.moduleCompiler.extractMetadata(moduleType);
    const token = dynamicMetadata
      ? moduleTokenFactory.createForDynamic(
          type,
          dynamicMetadata,
          moduleType as DynamicModule,
        )
      : moduleTokenFactory.createForStatic(type, moduleType as Type);

    const selectedModule = modulesContainer.get(token);
    if (!selectedModule) {
      throw new UnknownModuleException(type.name);
    }

    const options =
      typeof selectOptions?.abortOnError !== 'undefined'
        ? {
            ...this.appOptions,
            ...selectOptions,
          }
        : this.appOptions;

    return new NestApplicationContext(
      this.container,
      options,
      selectedModule,
      scope,
    );
  }

  public get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
  ): TResult;
  public get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    options: {
      strict?: boolean;
      each?: undefined | false;
    },
  ): TResult;
  public get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    options: {
      strict?: boolean;
      each: true;
    },
  ): Array<TResult>;
  public get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Abstract<TInput> | string | symbol,
    options: GetOrResolveOptions = { strict: false },
  ): TResult | Array<TResult> {
    return !(options && options.strict)
      ? this.find<TInput, TResult>(typeOrToken, options)
      : this.find<TInput, TResult>(typeOrToken, {
          moduleId: this.contextModule?.id,
          each: options.each,
        });
  }

  public resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
  ): Promise<TResult>;
  public resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    contextId?: {
      id: number;
    },
  ): Promise<TResult>;
  public resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    contextId?: {
      id: number;
    },
    options?: {
      strict?: boolean;
      each?: undefined | false;
    },
  ): Promise<TResult>;
  public resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    contextId?: {
      id: number;
    },
    options?: {
      strict?: boolean;
      each: true;
    },
  ): Promise<Array<TResult>>;
  public resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Abstract<TInput> | string | symbol,
    contextId = createContextId(),
    options: GetOrResolveOptions = { strict: false },
  ): Promise<TResult | Array<TResult>> {
    return this.resolvePerContext<TInput, TResult>(
      typeOrToken,
      this.contextModule!,
      contextId,
      options,
    );
  }

  public registerRequestByContextId<T = any>(request: T, contextId: ContextId) {
    this.container.registerRequestProvider(request, contextId);
  }

  public async init(): Promise<this> {
    if (this.isInitialized) {
      return this;
    }
    this.initializationPromise = this.callInitHook().then(() =>
      this.callBootstrapHook(),
    );
    await this.initializationPromise;

    this.isInitialized = true;
    return this;
  }

  public async close(signal?: string): Promise<void> {
    await this.initializationPromise;
    await this.prepareClose();
    await this.callDestroyHook();
    await this.callBeforeShutdownHook(signal);
    await this.dispose();
    await this.callShutdownHook(signal);
    this.unsubscribeFromProcessSignals();
  }

  public useLogger(logger: LoggerService | LogLevel[] | false) {
    Logger.overrideLogger(logger);

    if (this.shouldFlushLogsOnOverride) {
      this.flushLogs();
    }
  }

  public flushLogs() {
    Logger.flush();
  }

  public flushLogsOnOverride() {
    this.shouldFlushLogsOnOverride = true;
  }

  public enableShutdownHooks(
    signals: (ShutdownSignal | string)[] = [],
    options: ShutdownHooksOptions = {},
  ): this {
    if (!signals || isEmptyArray(signals)) {
      signals = Object.values(ShutdownSignal);
    } else {
      // given signals array should be unique because
      // process shouldn't listen to the same signal more than once.
      signals = Array.from(new Set(signals));
    }

    signals = iterate(signals)
      .map((signal: string) => signal.toString().toUpperCase().trim())
      // filter out the signals which is already listening to
      .filter(signal => !this.activeShutdownSignals.includes(signal))
      .toArray();

    this.listenToShutdownSignals(signals, options);
    return this;
  }

  protected async prepareClose(): Promise<void> {
    // Nest application context has no server
    // to signal, therefore just call a noop
    return Promise.resolve();
  }

  protected async dispose(): Promise<void> {
    // Nest application context has no server
    // to dispose, therefore just call a noop
    return Promise.resolve();
  }

  protected listenToShutdownSignals(
    signals: string[],
    options: ShutdownHooksOptions = {},
  ) {
    let receivedSignal = false;
    const cleanup = async (signal: string) => {
      try {
        if (receivedSignal) {
          // If we receive another signal while we're waiting
          // for the server to stop, just ignore it.
          return;
        }
        receivedSignal = true;
        await this.initializationPromise;
        await this.prepareClose();
        await this.callDestroyHook();
        await this.callBeforeShutdownHook(signal);
        await this.dispose();
        await this.callShutdownHook(signal);
        signals.forEach(sig => process.removeListener(sig, cleanup));

        if (options.useProcessExit) {
          // Use process.exit() to ensure the 'exit' event is properly triggered.
          // This is required for async loggers (like Pino with transports)
          // to flush their buffers before the process terminates.
          process.exit(0);
        } else {
          process.kill(process.pid, signal);
        }
      } catch (err) {
        Logger.error(
          MESSAGES.ERROR_DURING_SHUTDOWN,
          (err as Error)?.stack,
          NestApplicationContext.name,
        );
        process.exit(1);
      }
    };
    this.shutdownCleanupRef = cleanup as (...args: unknown[]) => unknown;

    signals.forEach((signal: string) => {
      this.activeShutdownSignals.push(signal);
      process.on(signal as any, cleanup);
    });
  }

  protected unsubscribeFromProcessSignals() {
    if (!this.shutdownCleanupRef) {
      return;
    }
    this.activeShutdownSignals.forEach(signal => {
      process.removeListener(signal, this.shutdownCleanupRef!);
    });
  }

  protected async callInitHook(): Promise<void> {
    const modulesSortedByDistance = this.getModulesToTriggerHooksOn();
    for (const module of modulesSortedByDistance) {
      await callModuleInitHook(module);
    }
  }

  protected async callDestroyHook(): Promise<void> {
    const modulesSortedByDistance = [
      ...this.getModulesToTriggerHooksOn(),
    ].reverse();

    for (const module of modulesSortedByDistance) {
      await callModuleDestroyHook(module);
    }
  }

  protected async callBootstrapHook(): Promise<void> {
    const modulesSortedByDistance = this.getModulesToTriggerHooksOn();
    for (const module of modulesSortedByDistance) {
      await callModuleBootstrapHook(module);
    }
  }

  protected async callShutdownHook(signal?: string): Promise<void> {
    const modulesSortedByDistance = [
      ...this.getModulesToTriggerHooksOn(),
    ].reverse();

    for (const module of modulesSortedByDistance) {
      await callAppShutdownHook(module, signal);
    }
  }

  protected async callBeforeShutdownHook(signal?: string): Promise<void> {
    const modulesSortedByDistance = [
      ...this.getModulesToTriggerHooksOn(),
    ].reverse();

    for (const module of modulesSortedByDistance) {
      await callBeforeAppShutdownHook(module, signal);
    }
  }

  protected assertNotInPreviewMode(methodName: string) {
    if (this.appOptions.preview) {
      const error = `Calling the "${methodName}" in the preview mode is not supported.`;
      this.logger.error(error);
      throw new Error(error);
    }
  }

  private getModulesToTriggerHooksOn(): Module[] {
    if (this._moduleRefsForHooksByDistance) {
      return this._moduleRefsForHooksByDistance;
    }
    const modulesContainer = this.container.getModules();
    const compareFn = (a: Module, b: Module) => b.distance - a.distance;
    const modulesSortedByDistance = Array.from(modulesContainer.values()).sort(
      compareFn,
    );

    this._moduleRefsForHooksByDistance = this.appOptions?.preview
      ? modulesSortedByDistance.filter(moduleRef => moduleRef.initOnPreview)
      : modulesSortedByDistance;
    return this._moduleRefsForHooksByDistance;
  }

  private printInPreviewModeWarning() {
    this.logger.warn('------------------------------------------------');
    this.logger.warn('Application is running in the PREVIEW mode!');
    this.logger.warn('Providers/controllers will not be instantiated.');
    this.logger.warn('------------------------------------------------');
  }
}
