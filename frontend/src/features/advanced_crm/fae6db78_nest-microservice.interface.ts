// @ts-nocheck
import { Observable } from 'rxjs';
import { ExceptionFilter } from './exceptions/exception-filter.interface.js';
import { CanActivate } from './features/can-activate.interface.js';
import { NestInterceptor } from './features/nest-interceptor.interface.js';
import { PipeTransform } from './features/pipe-transform.interface.js';
import { ITransportServer } from './microservices/transport-server.interface.js';
import { PreRequestHook } from './microservices/pre-request-hook.interface.js';
import { INestApplicationContext } from './nest-application-context.interface.js';
import { WebSocketAdapter } from './websockets/web-socket-adapter.interface.js';

export interface INestMicroservice extends INestApplicationContext {
  listen(): Promise<any>;

  useWebSocketAdapter(adapter: WebSocketAdapter): this;

  useGlobalFilters(...filters: ExceptionFilter[]): this;

  useGlobalPipes(...pipes: PipeTransform<any>[]): this;

  useGlobalInterceptors(...interceptors: NestInterceptor[]): this;

  useGlobalGuards(...guards: CanActivate[]): this;

  registerPreRequestHook(...hooks: PreRequestHook[]): this;

  close(): Promise<void>;

  status: Observable<string>;

  on<
    EventsMap extends Record<string, Function> = Record<string, Function>,
    EventKey extends keyof EventsMap = keyof EventsMap,
    EventCallback extends EventsMap[EventKey] = EventsMap[EventKey],
  >(
    event: EventKey,
    callback: EventCallback,
  ): void;

  unwrap<T>(): T;

  getTransportServer(): ITransportServer;
}
