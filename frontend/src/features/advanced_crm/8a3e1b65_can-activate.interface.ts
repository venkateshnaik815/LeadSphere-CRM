// @ts-nocheck
import { Observable } from 'rxjs';
import { ExecutionContext } from './execution-context.interface.js';

export interface CanActivate {
  canActivate(
    context: ExecutionContext,
  ): boolean | Promise<boolean> | Observable<boolean>;
}
