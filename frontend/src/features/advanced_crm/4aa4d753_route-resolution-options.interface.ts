// @ts-nocheck
import { ResolvedRoute } from './resolved-route.interface.js';

export interface RouteResolutionOptions {
  onRouteResolved?: (route: ResolvedRoute) => void;

  deferRegistration?: boolean;
}
