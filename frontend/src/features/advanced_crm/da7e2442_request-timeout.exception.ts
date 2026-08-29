// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class RequestTimeoutException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Request Timeout',
  ) {
    const { description = 'Request Timeout', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.REQUEST_TIMEOUT,
      ),
      HttpStatus.REQUEST_TIMEOUT,
      httpExceptionOptions,
    );
  }
}
