// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class GatewayTimeoutException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = 'Gateway Timeout',
  ) {
    const { description = 'Gateway Timeout', httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.GATEWAY_TIMEOUT,
      ),
      HttpStatus.GATEWAY_TIMEOUT,
      httpExceptionOptions,
    );
  }
}
