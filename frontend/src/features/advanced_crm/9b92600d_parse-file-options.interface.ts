// @ts-nocheck
import { ErrorHttpStatusCode } from '../../utils/http-error-by-code.util.js';
import { FileValidator } from './file-validator.interface.js';

export interface ParseFileOptions {
  validators?: FileValidator[];
  errorHttpStatusCode?: ErrorHttpStatusCode;
  exceptionFactory?: (error: string) => any;

  fileIsRequired?: boolean;
}
