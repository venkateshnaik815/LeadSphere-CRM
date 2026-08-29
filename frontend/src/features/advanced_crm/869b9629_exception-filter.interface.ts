// @ts-nocheck
import { ArgumentsHost } from '../features/arguments-host.interface.js';

export interface ExceptionFilter<T = any> {
  catch(exception: T, host: ArgumentsHost): any;
}
