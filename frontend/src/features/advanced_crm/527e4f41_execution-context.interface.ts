// @ts-nocheck
import { Type } from '../index.js';
import { ArgumentsHost } from './arguments-host.interface.js';

export interface ExecutionContext extends ArgumentsHost {
  getClass<T = any>(): Type<T>;
  getHandler(): Function;
}
