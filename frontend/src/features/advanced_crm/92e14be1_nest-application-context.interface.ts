// @ts-nocheck
import { ShutdownSignal } from '../enums/shutdown-signal.enum.js';
import { LoggerService, LogLevel } from '../services/logger.service.js';
import { DynamicModule } from './modules/index.js';
import { NestApplicationContextOptions } from './nest-application-context-options.interface.js';
import { ShutdownHooksOptions } from './shutdown-hooks-options.interface.js';
import { Type } from './type.interface.js';

export type SelectOptions = Pick<NestApplicationContextOptions, 'abortOnError'>;

export interface GetOrResolveOptions {
  strict?: boolean;
  each?: boolean;
}

export interface INestApplicationContext {
  select<T>(
    module: Type<T> | DynamicModule,
    options?: SelectOptions,
  ): INestApplicationContext;

  get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
  ): TResult;
  get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    options: { strict?: boolean; each?: undefined | false },
  ): TResult;
  get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    options: { strict?: boolean; each: true },
  ): Array<TResult>;
  get<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    options?: GetOrResolveOptions,
  ): TResult | Array<TResult>;

  resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
  ): Promise<TResult>;
  resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    contextId?: { id: number },
  ): Promise<TResult>;
  resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    contextId?: { id: number },
    options?: { strict?: boolean; each?: undefined | false },
  ): Promise<TResult>;
  resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    contextId?: { id: number },
    options?: { strict?: boolean; each: true },
  ): Promise<Array<TResult>>;
  resolve<TInput = any, TResult = TInput>(
    typeOrToken: Type<TInput> | Function | string | symbol,
    contextId?: { id: number },
    options?: GetOrResolveOptions,
  ): Promise<TResult | Array<TResult>>;

  registerRequestByContextId<T = any>(
    request: T,
    contextId: { id: number },
  ): void;

  close(): Promise<void>;

  useLogger(logger: LoggerService | LogLevel[] | false): void;

  flushLogs(): void;

  enableShutdownHooks(
    signals?: ShutdownSignal[] | string[],
    options?: ShutdownHooksOptions,
  ): this;

  init(): Promise<this>;
}
