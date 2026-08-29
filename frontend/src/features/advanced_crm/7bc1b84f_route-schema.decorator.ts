// @ts-nocheck
import { SetMetadata } from '@nestjs/common';
import { FASTIFY_ROUTE_SCHEMA_METADATA } from '../constants.js';
import { FastifySchema } from 'fastify';

export const RouteSchema = (schema: FastifySchema) =>
  SetMetadata(FASTIFY_ROUTE_SCHEMA_METADATA, schema);
