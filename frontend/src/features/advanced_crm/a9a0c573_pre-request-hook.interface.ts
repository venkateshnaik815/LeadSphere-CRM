// @ts-nocheck
import { Observable } from 'rxjs';
import { ExecutionContext } from '../features/execution-context.interface.js';

export interface PreRequestHook {
  (
    context: ExecutionContext,
    next: () => Observable<unknown>,
  ): Observable<unknown>;
}
