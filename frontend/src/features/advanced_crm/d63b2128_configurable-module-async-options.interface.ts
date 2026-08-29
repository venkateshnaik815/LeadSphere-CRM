// @ts-nocheck
import {
  FactoryProvider,
  ModuleMetadata,
  Provider,
  Type,
} from '../../interfaces/index.js';
import { DEFAULT_FACTORY_CLASS_METHOD_KEY } from '../constants.js';

export type ConfigurableModuleOptionsFactory<
  ModuleOptions,
  FactoryClassMethodKey extends string,
> = Record<
  `${FactoryClassMethodKey}`,
  () => Promise<ModuleOptions> | ModuleOptions
>;

export interface ConfigurableModuleAsyncOptions<
  ModuleOptions,
  FactoryClassMethodKey extends string =
    typeof DEFAULT_FACTORY_CLASS_METHOD_KEY,
> extends Pick<ModuleMetadata, 'imports'> {
  useExisting?: Type<
    ConfigurableModuleOptionsFactory<ModuleOptions, FactoryClassMethodKey>
  >;
  useClass?: Type<
    ConfigurableModuleOptionsFactory<ModuleOptions, FactoryClassMethodKey>
  >;
  useFactory?: (...args: any[]) => Promise<ModuleOptions> | ModuleOptions;
  inject?: FactoryProvider['inject'];
  provideInjectionTokensFrom?: Provider[];
}
