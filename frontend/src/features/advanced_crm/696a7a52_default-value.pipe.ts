// @ts-nocheck
import { Injectable } from '../decorators/core/injectable.decorator.js';
import {
  ArgumentMetadata,
  PipeTransform,
} from '../interfaces/features/pipe-transform.interface.js';
import { isNil, isNumber } from '../utils/shared.utils.js';

@Injectable()
export class DefaultValuePipe<T = any, R = any> implements PipeTransform<
  T,
  T | R
> {
  constructor(protected readonly defaultValue: R) {}

  transform(value?: T, _metadata?: ArgumentMetadata): T | R {
    if (
      isNil(value) ||
      (isNumber(value) && isNaN(value as unknown as number))
    ) {
      return this.defaultValue;
    }
    return value;
  }
}
