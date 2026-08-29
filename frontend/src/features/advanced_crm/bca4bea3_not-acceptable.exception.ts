// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class NotAcceptableException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Not Acceptable',
  ) {
    const { description = 'Not Acceptable', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.NOT_ACCEPTABLE,
      ),
      HttpStatus.NOT_ACCEPTABLE,
      httpExceptionOptions,
    );
  }
}
