// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class MethodNotAllowedException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Method Not Allowed',
  ) {
    const { description = 'Method Not Allowed', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.METHOD_NOT_ALLOWED,
      ),
      HttpStatus.METHOD_NOT_ALLOWED,
      httpExceptionOptions,
    );
  }
}
