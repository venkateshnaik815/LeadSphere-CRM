// @ts-nocheck
import { Injectable } from '../decorators/core/injectable.decorator.js';
import { Optional } from '../decorators/core/optional.decorator.js';
import { HttpStatus } from '../enums/http-status.enum.js';
import {
  ArgumentMetadata,
  PipeTransform,
} from '../interfaces/features/pipe-transform.interface.js';
import {
  ErrorHttpStatusCode,
  HttpErrorByCode,
} from '../utils/http-error-by-code.util.js';
import { isNil } from '../utils/shared.utils.js';

export interface ParseIntPipeOptions {
  errorHttpStatusCode?: ErrorHttpStatusCode;
  exceptionFactory?: (error: string) => any;
  optional?: boolean;
}

@Injectable()
export class ParseIntPipe implements PipeTransform {
  protected exceptionFactory: (error: string) => any;

  constructor(@Optional() protected readonly options?: ParseIntPipeOptions) {
    options = options || {};
    const { exceptionFactory, errorHttpStatusCode = HttpStatus.BAD_REQUEST } =
      options;

    this.exceptionFactory =
      exceptionFactory ||
      (error => new HttpErrorByCode[errorHttpStatusCode](error));
  }

  async transform(
    value: unknown,
    metadata: ArgumentMetadata,
  ): Promise<number | undefined | null> {
    if (isNil(value) && this.options?.optional) {
      return value;
    }
    if (!this.isNumeric(value)) {
      throw this.exceptionFactory(
        'Validation failed (numeric string is expected)',
      );
    }
    return parseInt(String(value), 10);
  }

  protected isNumeric(value: unknown): boolean {
    return (
      ['string', 'number'].includes(typeof value) &&
      /^-?\d+$/.test(String(value)) &&
      isFinite(value as any)
    );
  }
}
