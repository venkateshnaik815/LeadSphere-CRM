// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class InternalServerErrorException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions:
      | string
      | HttpExceptionOptions = 'Internal Server Error',
  ) {
    const { description = 'Internal Server Error', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.INTERNAL_SERVER_ERROR,
      ),
      HttpStatus.INTERNAL_SERVER_ERROR,
      httpExceptionOptions,
    );
  }
}
