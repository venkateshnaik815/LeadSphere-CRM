// @ts-nocheck
import type { ParameterDecoratorOptions } from '../decorators/http/route-params.decorator.js';
import { isFunction } from './shared.utils.js';

const isPipeLike = (value: any): boolean =>
  isFunction(value?.transform) ||
  (isFunction(value) &&
    value.prototype &&
    isFunction(value.prototype.transform));

export function isParameterDecoratorOptions(
  value: unknown,
): value is ParameterDecoratorOptions {
  return (
    !!value &&
    typeof value === 'object' &&
    !isPipeLike(value) &&
    ('schema' in value || 'pipes' in value)
  );
}
