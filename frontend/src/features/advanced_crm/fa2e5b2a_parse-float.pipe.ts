// @ts-nocheck
import { Injectable, Optional } from '../decorators/core/index.js';
import { ArgumentMetadata, HttpStatus } from '../index.js';
import { PipeTransform } from '../interfaces/features/pipe-transform.interface.js';
import {
  ErrorHttpStatusCode,
  HttpErrorByCode,
} from '../utils/http-error-by-code.util.js';
import { isNil } from '../utils/shared.utils.js';

export interface ParseFloatPipeOptions {
  errorHttpStatusCode?: ErrorHttpStatusCode;
  exceptionFactory?: (error: string) => any;
  optional?: boolean;
}

@Injectable()
export class ParseFloatPipe implements PipeTransform {
  protected exceptionFactory: (error: string) => any;

  constructor(@Optional() protected readonly options?: ParseFloatPipeOptions) {
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
    return parseFloat(String(value));
  }

  protected isNumeric(value: unknown): boolean {
    if (typeof value === 'number') return Number.isFinite(value);

    if (typeof value !== 'string' || value === '' || value !== value.trim()) {
      return false;
    }

    if (
      value.startsWith('0x') ||
      value.startsWith('0X') ||
      value.startsWith('0b') ||
      value.startsWith('0B') ||
      value.startsWith('0o') ||
      value.startsWith('0O')
    ) {
      return false;
    }

    return Number.isFinite(Number(value));
  }
}
