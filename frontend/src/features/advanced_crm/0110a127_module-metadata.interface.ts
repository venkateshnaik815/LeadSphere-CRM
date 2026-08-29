// @ts-nocheck
import { Abstract } from '../abstract.interface.js';
import { Type } from '../type.interface.js';
import { DynamicModule } from './dynamic-module.interface.js';
import { ForwardReference } from './forward-reference.interface.js';
import { Provider } from './provider.interface.js';

export interface ModuleMetadata {
  imports?: Array<
    Type<any> | DynamicModule | Promise<DynamicModule> | ForwardReference
  >;
  controllers?: Type<any>[];
  providers?: Provider[];
  exports?: Array<
    | DynamicModule
    | string
    | symbol
    | Provider
    | ForwardReference
    | Abstract<any>
    | Function
  >;
}
