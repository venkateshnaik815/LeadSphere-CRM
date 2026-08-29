// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class ServiceUnavailableException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Service Unavailable',
  ) {
    const { description = 'Service Unavailable', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.SERVICE_UNAVAILABLE,
      ),
      HttpStatus.SERVICE_UNAVAILABLE,
      httpExceptionOptions,
    );
  }
}
