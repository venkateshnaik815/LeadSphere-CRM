// @ts-nocheck
import {
  type ArgumentsHost,
  IntrinsicException,
  Logger,
  type WsExceptionFilter,
} from '@nestjs/common';
import { WsException } from '../errors/ws-exception.js';
import { isFunction, isNumber, isObject } from '@nestjs/common/internal';
import { MESSAGES } from '@nestjs/core/internal';

export interface ErrorPayload<Cause = { pattern: string; data: unknown }> {
  status: 'error';
  message: string;
  cause?: Cause;
}

interface BaseWsExceptionFilterOptions {
  includeCause?: boolean;

  causeFactory?: (pattern: string, data: unknown) => Record<string, any>;
}

export class BaseWsExceptionFilter<
  TError = any,
> implements WsExceptionFilter<TError> {
  protected static readonly logger = new Logger('WsExceptionsHandler');

  constructor(protected readonly options: BaseWsExceptionFilterOptions = {}) {
    this.options.includeCause = this.options.includeCause ?? true;
    this.options.causeFactory =
      this.options.causeFactory ?? ((pattern, data) => ({ pattern, data }));
  }

  public catch(exception: TError, host: ArgumentsHost) {
    const client = host.switchToWs().getClient();
    const pattern = host.switchToWs().getPattern();
    const data = host.switchToWs().getData();
    this.handleError(client, exception, {
      pattern,
      data,
    });
  }

  public handleError<TClient extends { emit?: Function; send?: Function }>(
    client: TClient,
    exception: TError,
    cause: ErrorPayload['cause'],
  ) {
    if (!(exception instanceof WsException)) {
      return this.handleUnknownError(exception, client, cause);
    }

    const status = 'error';
    const result = exception.getError();

    if (isObject(result)) {
      return this.emitMessage(client, 'exception', result);
    }

    const payload: ErrorPayload<unknown> = {
      status,
      message: result,
    };

    if (this.options?.includeCause && cause) {
      payload.cause = this.options.causeFactory!(cause.pattern, cause.data);
    }

    this.emitMessage(client, 'exception', payload);
  }

  public handleUnknownError<
    TClient extends { emit?: Function; send?: Function },
  >(exception: TError, client: TClient, data: ErrorPayload['cause']) {
    const status = 'error';
    const payload: ErrorPayload<unknown> = {
      status,
      message: MESSAGES.UNKNOWN_EXCEPTION_MESSAGE,
    };

    if (this.options?.includeCause && data) {
      payload.cause = this.options.causeFactory!(data.pattern, data.data);
    }

    this.emitMessage(client, 'exception', payload);

    if (!(exception instanceof IntrinsicException)) {
      const logger = BaseWsExceptionFilter.logger;
      logger.error(exception);
    }
  }

  public isExceptionObject(err: any): err is Error {
    return isObject(err) && !!(err as Error).message;
  }

  protected emitMessage<TClient extends { emit?: Function; send?: Function }>(
    client: TClient,
    event: string,
    payload: unknown,
  ): void {
    if (this.isNativeWebSocket(client)) {
      client.send(
        JSON.stringify({
          event,
          data: payload,
        }),
      );
    } else if (isFunction(client.emit)) {
      client.emit(event, payload);
    }
  }

  private isNativeWebSocket(
    client: Record<string, any>,
  ): client is { send: Function; readyState: number } {
    return isNumber(client.readyState) && isFunction(client.send);
  }
}
