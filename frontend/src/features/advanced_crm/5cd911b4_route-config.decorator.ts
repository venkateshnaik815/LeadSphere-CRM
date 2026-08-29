// @ts-nocheck
import { SetMetadata } from '@nestjs/common';
import { FASTIFY_ROUTE_CONFIG_METADATA } from '../constants.js';

export const RouteConfig = (config: any) =>
  SetMetadata(FASTIFY_ROUTE_CONFIG_METADATA, config);
