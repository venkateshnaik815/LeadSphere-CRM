// @ts-nocheck
import { Type } from '../type.interface.js';
import { RouteInfo } from './middleware-configuration.interface.js';
import { MiddlewareConsumer } from './middleware-consumer.interface.js';

export interface MiddlewareConfigProxy {
  exclude(...routes: (string | RouteInfo)[]): MiddlewareConfigProxy;

  forRoutes(...routes: (string | Type<any> | RouteInfo)[]): MiddlewareConsumer;
}
