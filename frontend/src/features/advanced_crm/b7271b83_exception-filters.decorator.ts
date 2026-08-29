// @ts-nocheck
import { EXCEPTION_FILTERS_METADATA } from '../../constants.js';
import { ExceptionFilter } from '../../index.js';
import { extendArrayMetadata } from '../../utils/extend-metadata.util.js';
import { isFunction } from '../../utils/shared.utils.js';
import { validateEach } from '../../utils/validate-each.util.js';


export const UseFilters = (...filters: (ExceptionFilter | Function)[]) =>
  addExceptionFiltersMetadata(...filters);

function addExceptionFiltersMetadata(
  ...filters: (Function | ExceptionFilter)[]
): MethodDecorator & ClassDecorator {
  return (
    target: any,
    key?: string | symbol,
    descriptor?: TypedPropertyDescriptor<any>,
  ) => {
    const isFilterValid = <T extends Function | Record<string, any>>(
      filter: T,
    ) => filter && (isFunction(filter) || isFunction(filter.catch));

    if (descriptor) {
      validateEach(
        target.constructor,
        filters,
        isFilterValid,
        '@UseFilters',
        'filter',
      );
      extendArrayMetadata(
        EXCEPTION_FILTERS_METADATA,
        filters,
        descriptor.value,
      );
      return descriptor;
    }
    validateEach(target, filters, isFilterValid, '@UseFilters', 'filter');
    extendArrayMetadata(EXCEPTION_FILTERS_METADATA, filters, target);
    return target;
  };
}
