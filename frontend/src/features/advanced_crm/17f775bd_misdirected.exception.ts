// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class MisdirectedException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Misdirected',
  ) {
    const { description = 'Misdirected', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.MISDIRECTED,
      ),
      HttpStatus.MISDIRECTED,
      httpExceptionOptions,
    );
  }
}
