// @ts-nocheck
import { HttpStatus } from '../enums/http-status.enum.js';
import { HttpException, HttpExceptionOptions } from './http.exception.js';

export class ImATeapotException extends HttpException {
  constructor(
    objectOrError?: any,
    descriptionOrOptions: string | HttpExceptionOptions = `I'm a teapot`,
  ) {
    const { description = `I'm a teapot`, httpExceptionOptions } =
      HttpException.extractDescriptionAndOptionsFrom(descriptionOrOptions);

    super(
      HttpException.createBody(
        objectOrError,
        description,
        HttpStatus.I_AM_A_TEAPOT,
      ),
      HttpStatus.I_AM_A_TEAPOT,
      httpExceptionOptions,
    );
  }
}
