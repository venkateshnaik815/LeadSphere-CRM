// @ts-nocheck
import type { StandardSchemaV1 } from '@standard-schema/spec';
import { Observable } from 'rxjs';
import { concatMap } from 'rxjs/operators';
import { Inject, Injectable, Optional } from '../decorators/core/index.js';
import { StreamableFile } from '../file-stream/index.js';
import {
  CallHandler,
  ExecutionContext,
  NestInterceptor,
} from '../interfaces/index.js';
import { isObject } from '../utils/shared.utils.js';
import { CLASS_SERIALIZER_OPTIONS } from './class-serializer.constants.js';
import { StandardSchemaSerializerContextOptions } from './standard-schema-serializer.interfaces.js';

interface PlainLiteralObject {
  [key: string]: any;
}

// NOTE (external)
// We need to deduplicate them here due to the circular dependency
// between core and common packages
const REFLECTOR = 'Reflector';

export interface StandardSchemaSerializerInterceptorOptions {
  schema?: StandardSchemaV1;
  validateOptions?: StandardSchemaV1.Options;
}

@Injectable()
export class StandardSchemaSerializerInterceptor implements NestInterceptor {
  constructor(
    @Inject(REFLECTOR) protected readonly reflector: any,
    @Optional()
    protected readonly defaultOptions: StandardSchemaSerializerInterceptorOptions = {},
  ) {}

  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    const contextOptions = this.getContextOptions(context);
    const schema = contextOptions?.schema ?? this.defaultOptions.schema;
    const validateOptions =
      contextOptions?.validateOptions ?? this.defaultOptions.validateOptions;

    return next
      .handle()
      .pipe(
        concatMap(async (res: PlainLiteralObject | Array<PlainLiteralObject>) =>
          this.serialize(res, schema, validateOptions),
        ),
      );
  }

  serialize(
    response: PlainLiteralObject | Array<PlainLiteralObject>,
    schema: StandardSchemaV1 | undefined,
    validateOptions?: StandardSchemaV1.Options,
  ):
    | PlainLiteralObject
    | Array<PlainLiteralObject>
    | Promise<PlainLiteralObject | Array<PlainLiteralObject>> {
    if (!schema || !isObject(response) || response instanceof StreamableFile) {
      return response;
    }

    return Array.isArray(response)
      ? Promise.all(
          response.map(item =>
            this.transformToPlain(item, schema, validateOptions),
          ),
        )
      : this.transformToPlain(response, schema, validateOptions);
  }

  async transformToPlain(
    plainOrClass: any,
    schema: StandardSchemaV1,
    validateOptions?: StandardSchemaV1.Options,
  ): Promise<PlainLiteralObject> {
    if (!plainOrClass) {
      return plainOrClass;
    }

    const result = await schema['~standard'].validate(
      plainOrClass,
      validateOptions,
    );

    if (result.issues) {
      throw new Error(
        `Serialization failed: ${result.issues.map(i => i.message).join(', ')}`,
      );
    }
    return result.value as PlainLiteralObject;
  }

  protected getContextOptions(
    context: ExecutionContext,
  ): StandardSchemaSerializerContextOptions | undefined {
    return this.reflector.getAllAndOverride(CLASS_SERIALIZER_OPTIONS, [
      context.getHandler(),
      context.getClass(),
    ]);
  }
}
