// @ts-nocheck
import {
  OPTIONAL_DEPS_METADATA,
  OPTIONAL_PROPERTY_DEPS_METADATA,
} from '../../constants.js';
import { isUndefined } from '../../utils/shared.utils.js';

export function Optional(): PropertyDecorator & ParameterDecorator {
  return (target: object, key: string | symbol | undefined, index?: number) => {
    if (!isUndefined(index)) {
      const args = Reflect.getOwnMetadata(OPTIONAL_DEPS_METADATA, target) || [];
      Reflect.defineMetadata(OPTIONAL_DEPS_METADATA, [...args, index], target);
      return;
    }
    const properties =
      Reflect.getOwnMetadata(
        OPTIONAL_PROPERTY_DEPS_METADATA,
        target.constructor,
      ) || [];
    Reflect.defineMetadata(
      OPTIONAL_PROPERTY_DEPS_METADATA,
      [...properties, key],
      target.constructor,
    );
  };
}
