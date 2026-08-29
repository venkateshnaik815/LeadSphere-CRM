// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class ConflictException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Conflict',
  ) {
    const { description = 'Conflict', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(objectOrError, description, HttpStatus.CONFLICT),
      HttpStatus.CONFLICT,
      httpExceptionOptions,
    );
  }
}
