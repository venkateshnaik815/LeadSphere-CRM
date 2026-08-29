// @ts-nocheck
import { FastifyCorsOptions } from '@fastify/cors';
import type { HttpServer, INestApplication } from '@nestjs/common';
import {
  FastifyBodyParser,
  FastifyInstance,
  FastifyListenOptions,
  FastifyPluginAsync,
  FastifyPluginCallback,
  FastifyPluginOptions,
  FastifyRegisterOptions,
  FastifyReply,
  FastifyRequest,
  RawServerBase,
  RawServerDefault,
} from 'fastify';
import {
  InjectOptions,
  Chain as LightMyRequestChain,
  Response as LightMyRequestResponse,
} from 'light-my-request';
import { FastifyStaticOptions, FastifyViewOptions } from './external/index.js';
import { NestFastifyBodyParserOptions } from './nest-fastify-body-parser-options.interface.js';

export interface NestFastifyApplication<
  TServer extends RawServerBase = RawServerDefault,
> extends INestApplication<TServer> {
  getHttpAdapter(): HttpServer<FastifyRequest, FastifyReply, FastifyInstance>;

  register<Options extends FastifyPluginOptions = any>(
    plugin:
      | FastifyPluginCallback<Options>
      | FastifyPluginAsync<Options>
      | Promise<{ default: FastifyPluginCallback<Options> }>
      | Promise<{ default: FastifyPluginAsync<Options> }>,
    opts?: FastifyRegisterOptions<Options>,
  ): Promise<FastifyInstance>;

  useBodyParser<TServer extends RawServerBase = RawServerBase>(
    type: string | string[] | RegExp,
    options?: NestFastifyBodyParserOptions,
    parser?: FastifyBodyParser<Buffer, TServer>,
  ): this;

  useStaticAssets(options: FastifyStaticOptions): this;

  enableCors(options?: FastifyCorsOptions): void;

  setViewEngine(options: FastifyViewOptions | string): this;

  inject(): LightMyRequestChain;
  inject(opts: InjectOptions | string): Promise<LightMyRequestResponse>;

  listen(
    opts: FastifyListenOptions,
    callback?: (err: Error | null, address: string) => void,
  ): Promise<TServer>;
  listen(opts?: FastifyListenOptions): Promise<TServer>;
  listen(
    callback?: (err: Error | null, address: string) => void,
  ): Promise<TServer>;
  listen(
    port: number | string,
    callback?: (err: Error | null, address: string) => void,
  ): Promise<TServer>;
  listen(
    port: number | string,
    address: string,
    callback?: (err: Error | null, address: string) => void,
  ): Promise<TServer>;
  listen(
    port: number | string,
    address: string,
    backlog: number,
    callback?: (err: Error | null, address: string) => void,
  ): Promise<TServer>;
}
