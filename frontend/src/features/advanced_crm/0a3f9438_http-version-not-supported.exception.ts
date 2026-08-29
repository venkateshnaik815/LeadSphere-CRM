// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class HttpVersionNotSupportedException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions:
      | string
      | HttpExceptionOptions = 'HTTP Version Not Supported',
  ) {
    const { description = 'HTTP Version Not Supported', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.HTTP_VERSION_NOT_SUPPORTED,
      ),
      HttpStatus.HTTP_VERSION_NOT_SUPPORTED,
      httpExceptionOptions,
    );
  }
}
