// @ts-nocheck
import { Injectable, Optional } from '../decorators/core/index.js';
import { ArgumentMetadata, HttpStatus } from '../index.js';
import { PipeTransform } from '../interfaces/features/pipe-transform.interface.js';
import {
  ErrorHttpStatusCode,
  HttpErrorByCode,
} from '../utils/http-error-by-code.util.js';
import { isNil } from '../utils/shared.utils.js';

export interface ParseEnumPipeOptions {
  optional?: boolean;
  errorHttpStatusCode?: ErrorHttpStatusCode;
  exceptionFactory?: (error: string) => any;
}

@Injectable()
export class ParseEnumPipe<T = any> implements PipeTransform<T> {
  protected exceptionFactory: (error: string) => any;
  constructor(
    protected readonly enumType: T,
    @Optional() protected readonly options?: ParseEnumPipeOptions,
  ) {
    if (!enumType) {
      throw new Error(
        `"ParseEnumPipe" requires "enumType" argument specified (to validate input values).`,
      );
    }
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
  ): Promise<T | undefined | null> {
    if (isNil(value) && this.options?.optional) {
      return value;
    }
    if (!this.isEnum(value)) {
      throw this.exceptionFactory(
        'Validation failed (enum string is expected)',
      );
    }
    return value as T;
  }

  protected isEnum(value: unknown): boolean {
    const enumValues = Object.keys(this.enumType as object)
      .filter(key => {
        const enumValue = (this.enumType as any)[key];
        return !(
          typeof enumValue === 'string' &&
          typeof (this.enumType as any)[enumValue] === 'number'
        );
      })
      .map(key => (this.enumType as any)[key]);
    return enumValues.includes(value);
  }
}
