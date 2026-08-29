// @ts-nocheck
import { MiddlewareConsumer } from '../middleware/middleware-consumer.interface.js';

export interface NestModule {
  configure(consumer: MiddlewareConsumer);
}
