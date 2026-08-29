// @ts-nocheck
import { Injectable } from '../decorators/core/injectable.decorator.js';
import { HttpStatus } from '../enums/http-status.enum.js';
import { PipeTransform } from '../interfaces/features/pipe-transform.interface.js';
import {
  ErrorHttpStatusCode,
  HttpErrorByCode,
} from '../utils/http-error-by-code.util.js';
import { isNil, isNumber, isString } from '../utils/shared.utils.js';

export interface ParseDatePipeOptions {
  optional?: boolean;
  default?: () => Date;
  errorHttpStatusCode?: ErrorHttpStatusCode;
  exceptionFactory?: (error: string) => any;
}

@Injectable()
export class ParseDatePipe implements PipeTransform {
  protected exceptionFactory: (error: string) => any;

  constructor(private readonly options: ParseDatePipeOptions = {}) {
    const { exceptionFactory, errorHttpStatusCode = HttpStatus.BAD_REQUEST } =
      options;

    this.exceptionFactory =
      exceptionFactory ||
      (error => new HttpErrorByCode[errorHttpStatusCode](error));
  }

  transform(value: unknown): Date | null | undefined {
    if (this.options.optional && isNil(value)) {
      return this.options.default ? this.options.default() : value;
    }

    if (isNil(value) || value === '') {
      throw this.exceptionFactory('Validation failed (no Date provided)');
    }

    const transformedValue =
      isString(value) || isNumber(value) || value instanceof Date
        ? new Date(value)
        : new Date(NaN);

    if (isNaN(transformedValue.getTime())) {
      throw this.exceptionFactory('Validation failed (invalid date format)');
    }

    return transformedValue;
  }
}
