// @ts-nocheck
import type { StandardSchemaV1 } from '@standard-schema/spec';
import { types } from 'util';
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

const BUILT_IN_TYPES = [Date, RegExp, Error, Map, Set, WeakMap, WeakSet];

export interface StandardSchemaValidationPipeOptions {
  transform?: boolean;
  validateCustomDecorators?: boolean;
  validateOptions?: Record<string, unknown>;
  errorHttpStatusCode?: ErrorHttpStatusCode;
  exceptionFactory?: (issues: readonly StandardSchemaV1.Issue[]) => any;
}

@Injectable()
export class StandardSchemaValidationPipe implements PipeTransform {
  protected isTransformEnabled: boolean;
  protected validateCustomDecorators: boolean;
  protected validateOptions: Record<string, unknown> | undefined;
  protected exceptionFactory: (
    issues: readonly StandardSchemaV1.Issue[],
  ) => any;

  constructor(
    @Optional()
    protected readonly options?: StandardSchemaValidationPipeOptions,
  ) {
    const {
      transform = true,
      validateCustomDecorators = false,
      validateOptions,
      exceptionFactory,
      errorHttpStatusCode = HttpStatus.BAD_REQUEST,
    } = options || {};

    this.isTransformEnabled = transform;
    this.validateCustomDecorators = validateCustomDecorators;
    this.validateOptions = validateOptions;

    this.exceptionFactory =
      exceptionFactory ||
      (issues => {
        const messages = this.formatIssueMessages(issues);
        return new HttpErrorByCode[errorHttpStatusCode](messages);
      });
  }

  protected formatIssueMessages(
    issues: readonly StandardSchemaV1.Issue[],
  ): string[] {
    return issues.map(issue => {
      if (issue.path?.length) {
        return `${issue.path.map(String).join('.')}: ${issue.message}`;
      }
      return issue.message;
    });
  }

  async transform<T = any>(value: T, metadata: ArgumentMetadata): Promise<T> {
    const schema = metadata.schema;
    if (!schema || !this.toValidate(metadata)) {
      return value;
    }

    this.stripProtoKeys(value);

    const result = await this.validate<T>(value, schema, this.validateOptions);

    if (result.issues) {
      throw this.exceptionFactory(result.issues);
    }
    return this.isTransformEnabled ? result.value : value;
  }

  protected toValidate(metadata: ArgumentMetadata): boolean {
    const { type } = metadata;
    if (type === 'custom' && !this.validateCustomDecorators) {
      return false;
    }
    return true;
  }

  protected validate<T = unknown>(
    value: unknown,
    schema: StandardSchemaV1,
    options?: Record<string, unknown>,
  ): Promise<StandardSchemaV1.Result<T>> | StandardSchemaV1.Result<T> {
    return schema['~standard'].validate(value, options) as
      | Promise<StandardSchemaV1.Result<T>>
      | StandardSchemaV1.Result<T>;
  }

  protected stripProtoKeys(value: any) {
    if (
      value == null ||
      typeof value !== 'object' ||
      types.isTypedArray(value)
    ) {
      return;
    }

    if (BUILT_IN_TYPES.some(type => value instanceof type)) {
      return;
    }

    if (Array.isArray(value)) {
      for (const v of value) {
        this.stripProtoKeys(v);
      }
      return;
    }

    delete value.__proto__;
    delete value.prototype;

    const constructorType = value?.constructor;
    if (constructorType && !BUILT_IN_TYPES.includes(constructorType)) {
      delete value.constructor;
    }

    for (const key in value) {
      this.stripProtoKeys(value[key]);
    }
  }
}
