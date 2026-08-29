// @ts-nocheck
import { Observable } from 'rxjs';
import { ArgumentsHost } from '../features/arguments-host.interface.js';

export interface RpcExceptionFilter<T = any, R = any> {
  catch(exception: T, host: ArgumentsHost): Observable<R>;
}
