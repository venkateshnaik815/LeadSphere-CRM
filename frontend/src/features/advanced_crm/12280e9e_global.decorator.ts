// @ts-nocheck
import { GLOBAL_MODULE_METADATA } from '../../constants.js';

export function Global(): ClassDecorator {
  return (target: Function) => {
    Reflect.defineMetadata(GLOBAL_MODULE_METADATA, true, target);
  };
}
