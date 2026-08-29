// @ts-nocheck
import { ModuleMetadata } from '../../interfaces/modules/module-metadata.interface.js';
import { validateModuleKeys } from '../../utils/validate-module-keys.util.js';

export function Module(metadata: ModuleMetadata): ClassDecorator {
  const propsKeys = Object.keys(metadata);
  validateModuleKeys(propsKeys);

  return (target: Function) => {
    for (const property in metadata) {
      if (Object.hasOwnProperty.call(metadata, property)) {
        Reflect.defineMetadata(property, (metadata as any)[property], target);
      }
    }
  };
}
