// @ts-nocheck
import { CanActivate } from './features/can-activate.interface.js';
import { NestInterceptor } from './features/nest-interceptor.interface.js';
import { GlobalPrefixOptions } from './global-prefix-options.interface.js';
import { HttpServer } from './http/http-server.interface.js';
import {
  ExceptionFilter,
  INestMicroservice,
  NestHybridApplicationOptions,
  PipeTransform,
} from './index.js';
import { INestApplicationContext } from './nest-application-context.interface.js';
import { VersioningOptions } from './version-options.interface.js';
import { WebSocketAdapter } from './websockets/web-socket-adapter.interface.js';

export interface INestApplication<
  TServer = any,
> extends INestApplicationContext {
  use(...args: any[]): this;

  enableCors(options?: any): void;

  enableVersioning(options?: VersioningOptions): this;

  listen(port: number | string, callback?: () => void): Promise<any>;
  listen(
    port: number | string,
    hostname: string,
    callback?: () => void,
  ): Promise<any>;

  getUrl(): Promise<string>;

  setGlobalPrefix(prefix: string, options?: GlobalPrefixOptions): this;

  useWebSocketAdapter(adapter: WebSocketAdapter): this;

  connectMicroservice<T extends object = any>(
    options: T,
    hybridOptions?: NestHybridApplicationOptions,
  ): INestMicroservice;

  getMicroservices(): INestMicroservice[];

  getHttpServer(): TServer;

  getHttpAdapter(): HttpServer;

  startAllMicroservices(): Promise<this>;

  useGlobalFilters(...filters: ExceptionFilter[]): this;

  useGlobalPipes(...pipes: PipeTransform<any>[]): this;

  useGlobalInterceptors(...interceptors: NestInterceptor[]): this;

  useGlobalGuards(...guards: CanActivate[]): this;

  close(): Promise<void>;
}
