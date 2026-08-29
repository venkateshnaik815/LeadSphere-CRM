// @ts-nocheck
import {
  HttpExceptionBody,
  HttpExceptionBodyMessage,
} from '../interfaces/http/http-exception-body.interface.js';
import { isNumber, isObject, isString } from '../utils/shared.utils.js';
import { IntrinsicException } from './intrinsic.exception.js';

export interface HttpExceptionOptions {
  cause?: unknown;
  description?: string;
  errorCode?: string;
}

export interface DescriptionAndOptions {
  description?: string;
  httpExceptionOptions?: HttpExceptionOptions;
}

export class HttpException extends IntrinsicException {
  public cause: unknown;
  public errorCode?: string;

  constructor(
    private readonly response: string | Record<string, any>,
    private readonly status: number,
    private readonly options?: HttpExceptionOptions,
  ) {
    super();
    this.initMessage();
    this.initName();
    this.initCause();
    this.initErrorCode();
  }

  public initCause(): void {
    if (this.options?.cause) {
      this.cause = this.options.cause;
      return;
    }
  }

  public initErrorCode(): void {
    if (this.options?.errorCode) {
      this.errorCode = this.options.errorCode;
    }
  }

  public initMessage() {
    if (isString(this.response)) {
      this.message = this.response;
    } else if (isObject(this.response) && isString(this.response.message)) {
      this.message = this.response.message;
    } else if (this.constructor) {
      this.message =
        this.constructor.name.match(/[A-Z][a-z]+|[0-9]+/g)?.join(' ') ??
        'Error';
    }
  }

  public initName(): void {
    this.name = this.constructor.name;
  }

  public getResponse(): string | object {
    return this.response;
  }

  public getStatus(): number {
    return this.status;
  }

  public static createBody(
    nil: null | '',
    message: HttpExceptionBodyMessage,
    statusCode: number,
  ): HttpExceptionBody;
  public static createBody(
    message: HttpExceptionBodyMessage,
    error: string,
    statusCode: number,
  ): HttpExceptionBody;
  public static createBody(
    message: HttpExceptionBodyMessage,
    error: string,
    statusCode: number,
    errorCode?: string,
  ): HttpExceptionBody;
  public static createBody<Body extends Record<string, unknown>>(
    custom: Body,
  ): Body;
  public static createBody<Body extends Record<string, unknown>>(
    arg0: null | HttpExceptionBodyMessage | Body,
    arg1?: HttpExceptionBodyMessage | string,
    statusCode?: number,
    errorCode?: string,
  ): HttpExceptionBody | Body {
    if (!arg0) {
      const body: HttpExceptionBody = {
        message: arg1!,
        statusCode: statusCode!,
      };
      if (errorCode) {
        body.errorCode = errorCode;
      }
      return body;
    }

    if (isString(arg0) || Array.isArray(arg0) || isNumber(arg0)) {
      const body: HttpExceptionBody = {
        message: arg0,
        error: arg1 as string,
        statusCode: statusCode!,
      };
      if (errorCode) {
        body.errorCode = errorCode;
      }
      return body;
    }

    return arg0;
  }

  public static getDescriptionFrom(
    descriptionOrOptions: string | HttpExceptionOptions,
  ): string {
    return isString(descriptionOrOptions)
      ? descriptionOrOptions
      : (descriptionOrOptions?.description as string);
  }

  public static getHttpExceptionOptionsFrom(
    descriptionOrOptions: string | HttpExceptionOptions,
  ): HttpExceptionOptions {
    return isString(descriptionOrOptions) ? {} : descriptionOrOptions;
  }

  public static extractDescriptionAndOptionsFrom(
    descriptionOrOptions: string | HttpExceptionOptions,
  ): DescriptionAndOptions {
    const description = isString(descriptionOrOptions)
      ? descriptionOrOptions
      : descriptionOrOptions?.description;

    const httpExceptionOptions = isString(descriptionOrOptions)
      ? {}
      : descriptionOrOptions;

    return {
      description,
      httpExceptionOptions,
    };
  }
}
