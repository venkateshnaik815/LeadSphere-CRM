// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class UnsupportedMediaTypeException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions:
      | string
      | HttpExceptionOptions = 'Unsupported Media Type',
  ) {
    const { description = 'Unsupported Media Type', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
      ),
      HttpStatus.UNSUPPORTED_MEDIA_TYPE,
      httpExceptionOptions,
    );
  }
}
