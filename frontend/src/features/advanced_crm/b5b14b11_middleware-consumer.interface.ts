// @ts-nocheck
import { Type } from '../type.interface.js';
import { MiddlewareConfigProxy } from './middleware-config-proxy.interface.js';

export interface MiddlewareConsumer {
  apply(...middleware: (Type<any> | Function)[]): MiddlewareConfigProxy;
}
