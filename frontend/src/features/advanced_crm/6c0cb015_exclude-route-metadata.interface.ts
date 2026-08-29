// @ts-nocheck
import type { RequestMethod } from '@nestjs/common';

export interface ExcludeRouteMetadata {
  path: string;

  pathRegex: RegExp;

  requestMethod: RequestMethod;
}
