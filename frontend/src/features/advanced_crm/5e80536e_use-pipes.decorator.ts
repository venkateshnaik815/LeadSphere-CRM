// @ts-nocheck
import { PIPES_METADATA } from '../../constants.js';
import { PipeTransform } from '../../interfaces/index.js';
import { extendArrayMetadata } from '../../utils/extend-metadata.util.js';
import { isFunction } from '../../utils/shared.utils.js';
import { validateEach } from '../../utils/validate-each.util.js';


export function UsePipes(
  ...pipes: (PipeTransform | Function)[]
): ClassDecorator & MethodDecorator {
  return (
    target: any,
    key?: string | symbol,
    descriptor?: TypedPropertyDescriptor<any>,
  ) => {
    const isPipeValid = <T extends Function | Record<string, any>>(pipe: T) =>
      pipe && (isFunction(pipe) || isFunction(pipe.transform));

    if (descriptor) {
      validateEach(target.constructor, pipes, isPipeValid, '@UsePipes', 'pipe');
      extendArrayMetadata(PIPES_METADATA, pipes, descriptor.value);
      return descriptor;
    }
    validateEach(target, pipes, isPipeValid, '@UsePipes', 'pipe');
    extendArrayMetadata(PIPES_METADATA, pipes, target);
    return target;
  };
}
