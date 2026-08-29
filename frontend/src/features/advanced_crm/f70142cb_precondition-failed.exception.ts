// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class PreconditionFailedException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Precondition Failed',
  ) {
    const { description = 'Precondition Failed', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.PRECONDITION_FAILED,
      ),
      HttpStatus.PRECONDITION_FAILED,
      httpExceptionOptions,
    );
  }
}
