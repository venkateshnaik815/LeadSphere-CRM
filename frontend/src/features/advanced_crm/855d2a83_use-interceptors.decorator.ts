// @ts-nocheck
import { INTERCEPTORS_METADATA } from '../../constants.js';
import { NestInterceptor } from '../../interfaces/index.js';
import { extendArrayMetadata } from '../../utils/extend-metadata.util.js';
import { isFunction } from '../../utils/shared.utils.js';
import { validateEach } from '../../utils/validate-each.util.js';

export function UseInterceptors(
  ...interceptors: (NestInterceptor | Function)[]
): MethodDecorator & ClassDecorator {
  return (
    target: any,
    key?: string | symbol,
    descriptor?: TypedPropertyDescriptor<any>,
  ) => {
    const isInterceptorValid = <T extends Function | Record<string, any>>(
      interceptor: T,
    ) =>
      interceptor &&
      (isFunction(interceptor) || isFunction(interceptor.intercept));

    if (descriptor) {
      validateEach(
        target.constructor,
        interceptors,
        isInterceptorValid,
        '@UseInterceptors',
        'interceptor',
      );
      extendArrayMetadata(
        INTERCEPTORS_METADATA,
        interceptors,
        descriptor.value,
      );
      return descriptor;
    }
    validateEach(
      target,
      interceptors,
      isInterceptorValid,
      '@UseInterceptors',
      'interceptor',
    );
    extendArrayMetadata(INTERCEPTORS_METADATA, interceptors, target);
    return target;
  };
}
