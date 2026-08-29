// @ts-nocheck
import { DynamicModule, Provider } from '../interfaces/index.js';
import { Logger } from '../services/logger.service.js';
import { randomStringGenerator } from '../utils/random-string-generator.util.js';
import {
  ASYNC_METHOD_SUFFIX,
  ASYNC_OPTIONS_METADATA_KEYS,
  CONFIGURABLE_MODULE_ID,
  DEFAULT_FACTORY_CLASS_METHOD_KEY,
  DEFAULT_METHOD_KEY,
} from './constants.js';
import {
  ConfigurableModuleAsyncOptions,
  ConfigurableModuleCls,
  ConfigurableModuleHost,
  ConfigurableModuleOptionsFactory,
} from './interfaces/index.js';
import {
  generateOptionsInjectionToken,
  getInjectionProviders,
} from './utils/index.js';

export interface ConfigurableModuleBuilderOptions {
  optionsInjectionToken?: string | symbol;
  moduleName?: string;
  alwaysTransient?: boolean;
}

export class ConfigurableModuleBuilder<
  ModuleOptions,
  StaticMethodKey extends string = typeof DEFAULT_METHOD_KEY,
  FactoryClassMethodKey extends string =
    typeof DEFAULT_FACTORY_CLASS_METHOD_KEY,
  ExtraModuleDefinitionOptions = {},
> {
  protected staticMethodKey: StaticMethodKey;
  protected factoryClassMethodKey: FactoryClassMethodKey;
  protected extras: ExtraModuleDefinitionOptions;
  protected transformModuleDefinition: (
    definition: DynamicModule,
    extraOptions: ExtraModuleDefinitionOptions,
  ) => DynamicModule;

  protected readonly logger = new Logger(ConfigurableModuleBuilder.name);

  constructor(
    protected readonly options: ConfigurableModuleBuilderOptions = {},
    parentBuilder?: ConfigurableModuleBuilder<ModuleOptions>,
  ) {
    if (parentBuilder) {
      this.staticMethodKey = parentBuilder.staticMethodKey as StaticMethodKey;
      this.factoryClassMethodKey =
        parentBuilder.factoryClassMethodKey as FactoryClassMethodKey;
      this.transformModuleDefinition =
        parentBuilder.transformModuleDefinition as (
          definition: DynamicModule,
          extraOptions: ExtraModuleDefinitionOptions,
        ) => DynamicModule;
      this.extras = parentBuilder.extras as ExtraModuleDefinitionOptions;
    }
  }

  setExtras<ExtraModuleDefinitionOptions>(
    extras: ExtraModuleDefinitionOptions,
    transformDefinition: (
      definition: DynamicModule,
      extras: ExtraModuleDefinitionOptions,
    ) => DynamicModule = def => def,
  ) {
    const builder = new ConfigurableModuleBuilder<
      ModuleOptions,
      StaticMethodKey,
      FactoryClassMethodKey,
      ExtraModuleDefinitionOptions
    >(this.options, this as any);
    builder.extras = extras;
    builder.transformModuleDefinition = transformDefinition;
    return builder;
  }

  setClassMethodName<StaticMethodKey extends string>(key: StaticMethodKey) {
    const builder = new ConfigurableModuleBuilder<
      ModuleOptions,
      StaticMethodKey,
      FactoryClassMethodKey,
      ExtraModuleDefinitionOptions
    >(this.options, this as any);
    builder.staticMethodKey = key;
    return builder;
  }

  setFactoryMethodName<FactoryClassMethodKey extends string>(
    key: FactoryClassMethodKey,
  ) {
    const builder = new ConfigurableModuleBuilder<
      ModuleOptions,
      StaticMethodKey,
      FactoryClassMethodKey,
      ExtraModuleDefinitionOptions
    >(this.options, this as any);
    builder.factoryClassMethodKey = key;
    return builder;
  }

  build(): ConfigurableModuleHost<
    ModuleOptions,
    StaticMethodKey,
    FactoryClassMethodKey,
    ExtraModuleDefinitionOptions
  > {
    this.staticMethodKey ??= DEFAULT_METHOD_KEY as StaticMethodKey;
    this.factoryClassMethodKey ??=
      DEFAULT_FACTORY_CLASS_METHOD_KEY as FactoryClassMethodKey;
    this.options.optionsInjectionToken ??= this.options.moduleName
      ? this.constructInjectionTokenString()
      : generateOptionsInjectionToken();
    this.transformModuleDefinition ??= definition => definition;

    return {
      ConfigurableModuleClass:
        this.createConfigurableModuleCls<ModuleOptions>(),
      MODULE_OPTIONS_TOKEN: this.options.optionsInjectionToken,
      ASYNC_OPTIONS_TYPE: this.createTypeProxy('ASYNC_OPTIONS_TYPE'),
      OPTIONS_TYPE: this.createTypeProxy('OPTIONS_TYPE'),
    };
  }

  private constructInjectionTokenString(): string {
    const moduleNameInSnakeCase = this.options
      .moduleName!.trim()
      .split(/(?=[A-Z])/)
      .join('_')
      .toUpperCase();
    return `${moduleNameInSnakeCase}_MODULE_OPTIONS`;
  }

  private createConfigurableModuleCls<ModuleOptions>(): ConfigurableModuleCls<
    ModuleOptions,
    StaticMethodKey,
    FactoryClassMethodKey
  > {
    // eslint-disable-next-line @typescript-eslint/no-this-alias
    const self = this;
    const asyncMethodKey = this.staticMethodKey + ASYNC_METHOD_SUFFIX;

    class InternalModuleClass {
      static [self.staticMethodKey](
        options: ModuleOptions & ExtraModuleDefinitionOptions,
      ): DynamicModule {
        const providers: Array<Provider> = [
          {
            provide: self.options.optionsInjectionToken!,
            useValue: this.omitExtras(options, self.extras),
          },
        ];
        if (self.options.alwaysTransient) {
          providers.push({
            provide: CONFIGURABLE_MODULE_ID,
            useValue: randomStringGenerator(),
          });
        }
        return self.transformModuleDefinition(
          {
            module: this,
            providers,
          },
          {
            ...self.extras,
            ...options,
          },
        );
      }

      static [asyncMethodKey](
        options: ConfigurableModuleAsyncOptions<ModuleOptions> &
          ExtraModuleDefinitionOptions,
      ): DynamicModule {
        const providers = this.createAsyncProviders(options);
        if (self.options.alwaysTransient) {
          providers.push({
            provide: CONFIGURABLE_MODULE_ID,
            useValue: randomStringGenerator(),
          });
        }
        return self.transformModuleDefinition(
          {
            module: this,
            imports: options.imports || [],
            providers,
          },
          {
            ...self.extras,
            ...this.extractExtrasFromAsyncOptions(options, self.extras),
          },
        );
      }

      private static omitExtras(
        input: ModuleOptions & ExtraModuleDefinitionOptions,
        extras: ExtraModuleDefinitionOptions | undefined,
      ): ModuleOptions {
        if (!extras) {
          return input;
        }
        const moduleOptions = {};
        const extrasKeys = Object.keys(extras);

        Object.keys(input as object)
          .filter(key => !extrasKeys.includes(key))
          .forEach(key => {
            moduleOptions[key] = input[key];
          });
        return moduleOptions as ModuleOptions;
      }

      private static extractExtrasFromAsyncOptions(
        input: ConfigurableModuleAsyncOptions<ModuleOptions> &
          ExtraModuleDefinitionOptions,
        extras: ExtraModuleDefinitionOptions | undefined,
      ): Partial<ExtraModuleDefinitionOptions> {
        if (!extras) {
          return {};
        }
        const extrasOptions = {};

        Object.keys(input as object)
          .filter(key => !ASYNC_OPTIONS_METADATA_KEYS.includes(key as any))
          .forEach(key => {
            extrasOptions[key] = input[key];
          });

        return extrasOptions;
      }

      private static createAsyncProviders(
        options: ConfigurableModuleAsyncOptions<ModuleOptions> &
          ExtraModuleDefinitionOptions,
      ): Provider[] {
        if (options.useExisting || options.useFactory) {
          if (options.inject && options.provideInjectionTokensFrom) {
            return [
              this.createAsyncOptionsProvider(options),
              ...getInjectionProviders(
                options.provideInjectionTokensFrom,
                options.inject,
              ),
            ];
          }
          return [this.createAsyncOptionsProvider(options)];
        }
        return [
          this.createAsyncOptionsProvider(options),
          {
            provide: options.useClass!,
            useClass: options.useClass!,
          },
        ];
      }

      private static createAsyncOptionsProvider(
        options: ConfigurableModuleAsyncOptions<ModuleOptions>,
      ): Provider {
        if (options.useFactory) {
          return {
            provide: self.options.optionsInjectionToken!,
            useFactory: options.useFactory,
            inject: options.inject || [],
          };
        }
        return {
          provide: self.options.optionsInjectionToken!,
          useFactory: async (
            optionsFactory: ConfigurableModuleOptionsFactory<
              ModuleOptions,
              FactoryClassMethodKey
            >,
          ) =>
            await optionsFactory[
              self.factoryClassMethodKey as keyof typeof optionsFactory
            ](),
          inject: [options.useExisting || options.useClass!],
        };
      }
    }
    return InternalModuleClass as unknown as ConfigurableModuleCls<
      ModuleOptions,
      StaticMethodKey,
      FactoryClassMethodKey
    >;
  }

  private createTypeProxy(
    typeName: 'OPTIONS_TYPE' | 'ASYNC_OPTIONS_TYPE' | 'OptionsFactoryInterface',
  ) {
    const proxy = new Proxy(
      {},
      {
        get: () => {
          throw new Error(
            `"${typeName}" is not supposed to be used as a value.`,
          );
        },
      },
    );
    return proxy as any;
  }
}
