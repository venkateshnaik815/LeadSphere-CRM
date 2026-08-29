// @ts-nocheck
import { Abstract } from '../abstract.interface.js';
import { Type } from '../type.interface.js';

export type InjectionToken<T = any> =
  | string
  | symbol
  | Type<T>
  | Abstract<T>
  | Function;
