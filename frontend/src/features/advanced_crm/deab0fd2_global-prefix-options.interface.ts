// @ts-nocheck
import { RouteInfo } from './middleware/index.js';

export interface GlobalPrefixOptions<T = string | RouteInfo> {
  exclude?: T[];
}
