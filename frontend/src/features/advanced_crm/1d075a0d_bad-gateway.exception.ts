// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class BadGatewayException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Bad Gateway',
  ) {
    const { description = 'Bad Gateway', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.BAD_GATEWAY,
      ),
      HttpStatus.BAD_GATEWAY,
      httpExceptionOptions,
    );
  }
}
