// @ts-nocheck
import type { HttpServer, INestApplication } from '@nestjs/common';
import type { Express } from 'express';
import type { Server as CoreHttpServer } from 'http';
import type { Server as CoreHttpsServer } from 'https';
import {
  NestExpressBodyParserOptionsFor,
  NestExpressBodyParserType,
} from './nest-express-body-parser.interface.js';
import { ServeStaticOptions } from './serve-static-options.interface.js';
import type { CorsOptions, CorsOptionsDelegate } from '@nestjs/common/internal';

export interface NestExpressApplication<
  TServer extends CoreHttpServer | CoreHttpsServer = CoreHttpServer,
> extends INestApplication<TServer> {
  getHttpAdapter(): HttpServer<Express.Request, Express.Response, Express>;

  listen(port: number | string, callback?: () => void): Promise<TServer>;
  listen(
    port: number | string,
    hostname: string,
    callback?: () => void,
  ): Promise<TServer>;

  set(...args: any[]): this;

  engine(...args: any[]): this;

  enable(...args: any[]): this;

  disable(...args: any[]): this;

  useStaticAssets(options: ServeStaticOptions): this;
  useStaticAssets(path: string, options?: ServeStaticOptions): this;

  enableCors(options?: CorsOptions | CorsOptionsDelegate<any>): void;

  useBodyParser<ParserType extends NestExpressBodyParserType>(
    parser: ParserType,
    options?: NestExpressBodyParserOptionsFor<ParserType>,
  ): this;

  setBaseViewsDir(path: string | string[]): this;

  setViewEngine(engine: string): this;

  setLocal(key: string, value: any): this;
}
