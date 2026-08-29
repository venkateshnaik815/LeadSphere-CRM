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

export interface ParseBoolPipeOptions {
  errorHttpStatusCode?: ErrorHttpStatusCode;
  exceptionFactory?: (error: string) => any;
  optional?: boolean;
}

@Injectable()
export class ParseBoolPipe implements PipeTransform {
  protected exceptionFactory: (error: string) => any;

  constructor(@Optional() protected readonly options?: ParseBoolPipeOptions) {
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
  ): Promise<boolean | undefined | null> {
    if (isNil(value) && this.options?.optional) {
      return value;
    }
    if (this.isTrue(value)) {
      return true;
    }
    if (this.isFalse(value)) {
      return false;
    }
    throw this.exceptionFactory(
      'Validation failed (boolean string is expected)',
    );
  }

  protected isTrue(value: unknown): boolean {
    return value === true || value === 'true';
  }

  protected isFalse(value: unknown): boolean {
    return value === false || value === 'false';
  }
}
