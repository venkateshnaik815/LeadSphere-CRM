// @ts-nocheck
import { ConfigurableModuleAsyncOptions } from './configurable-module-async-options.interface.js';
import { ConfigurableModuleCls } from './configurable-module-cls.interface.js';

export interface ConfigurableModuleHost<
  ModuleOptions = Record<string, unknown>,
  MethodKey extends string = string,
  FactoryClassMethodKey extends string = string,
  ExtraModuleDefinitionOptions = {},
> {
  ConfigurableModuleClass: ConfigurableModuleCls<
    ModuleOptions,
    MethodKey,
    FactoryClassMethodKey,
    ExtraModuleDefinitionOptions
  >;
  MODULE_OPTIONS_TOKEN: string | symbol;
  ASYNC_OPTIONS_TYPE: ConfigurableModuleAsyncOptions<
    ModuleOptions,
    FactoryClassMethodKey
  > &
    Partial<ExtraModuleDefinitionOptions>;
  OPTIONS_TYPE: ModuleOptions & Partial<ExtraModuleDefinitionOptions>;
}
