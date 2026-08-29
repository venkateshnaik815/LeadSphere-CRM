// @ts-nocheck
import { Type } from '../type.interface.js';
import { ModuleMetadata } from './module-metadata.interface.js';

export interface DynamicModule extends ModuleMetadata {
  module: Type<any>;

  global?: boolean;
}
