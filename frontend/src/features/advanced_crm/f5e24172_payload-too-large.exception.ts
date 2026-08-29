// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class PayloadTooLargeException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Payload Too Large',
  ) {
    const { description = 'Payload Too Large', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.PAYLOAD_TOO_LARGE,
      ),
      HttpStatus.PAYLOAD_TOO_LARGE,
      httpExceptionOptions,
    );
  }
}
