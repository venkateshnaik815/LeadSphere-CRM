// @ts-nocheck
import type { IncomingMessage } from 'http';

export interface NestExpressBodyParserOptions {
  inflate?: boolean | undefined;

  limit?: number | string | undefined;

  type?: string | string[] | ((req: IncomingMessage) => any) | undefined;

  // Catch-all for body-parser type specific options
  [key: string]: unknown;
}
