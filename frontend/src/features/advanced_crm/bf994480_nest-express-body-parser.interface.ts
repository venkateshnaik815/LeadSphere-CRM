// @ts-nocheck
type Express = typeof import('express');

export interface NestExpressBodyParserOptionsMap {
  json: NonNullable<Parameters<Express['json']>[0]>;
  urlencoded: NonNullable<Parameters<Express['urlencoded']>[0]>;
  text: NonNullable<Parameters<Express['text']>[0]>;
  raw: NonNullable<Parameters<Express['raw']>[0]>;
}

export type NestExpressBodyParserType = keyof NestExpressBodyParserOptionsMap;

export type NestExpressBodyParserOptionsFor<
  ParserType extends NestExpressBodyParserType,
> = Omit<NestExpressBodyParserOptionsMap[ParserType], 'verify'>;
