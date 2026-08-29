// @ts-nocheck
import { ResolvedRoute } from './resolved-route.interface.js';

export type ConflictKind = 'duplicate' | 'shadow';

export interface RouteConflict {
  winner: ResolvedRoute;
  shadowed: ResolvedRoute;
  kind: ConflictKind;
}
