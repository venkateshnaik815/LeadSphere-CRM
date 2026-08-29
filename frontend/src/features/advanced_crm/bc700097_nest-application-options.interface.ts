// @ts-nocheck
import {
  CorsOptions,
  CorsOptionsDelegate,
} from './external/cors-options.interface.js';
import { HttpsOptions } from './external/https-options.interface.js';
import { NestApplicationContextOptions } from './nest-application-context-options.interface.js';
import {
  RouteConflictPolicy,
  RouteResolutionStrategy,
} from './router-options.interface.js';

export interface NestApplicationOptions extends NestApplicationContextOptions {
  cors?: boolean | CorsOptions | CorsOptionsDelegate<any>;
  bodyParser?: boolean;
  httpsOptions?: HttpsOptions;
  rawBody?: boolean;
  forceCloseConnections?: boolean;
  return503OnClosing?: boolean;
  routeConflictPolicy?: RouteConflictPolicy;
  routeResolutionStrategy?: RouteResolutionStrategy;
}
