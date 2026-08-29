// @ts-nocheck
import { FileValidatorContext } from './file-validator-context.interface.js';
import { FileValidator } from './file-validator.interface.js';
import { IFile } from './interfaces/index.js';

type MaxFileSizeValidatorContext = FileValidatorContext<
  Omit<MaxFileSizeValidatorOptions, 'errorMessage' | 'message'>
>;

export type MaxFileSizeValidatorOptions = {
  maxSize: number;

  message?: string | ((maxSize: number) => string);

  errorMessage?: string | ((ctx: MaxFileSizeValidatorContext) => string);
};

export class MaxFileSizeValidator extends FileValidator<
  MaxFileSizeValidatorOptions,
  IFile
> {
  buildErrorMessage(file?: IFile): string {
    const { errorMessage, message, ...config } = this.validationOptions;

    if (errorMessage) {
      return typeof errorMessage === 'function'
        ? errorMessage({ file, config })
        : errorMessage;
    }

    if (message) {
      return typeof message === 'function'
        ? message(this.validationOptions.maxSize)
        : message;
    }

    if (file?.size) {
      return `Validation failed (current file size is ${file.size}, expected size is less than ${this.validationOptions.maxSize})`;
    }
    return `Validation failed (expected size is less than ${this.validationOptions.maxSize})`;
  }

  public isValid(file?: IFile): boolean {
    if (!this.validationOptions || !file) {
      return true;
    }

    return 'size' in file && file.size < this.validationOptions.maxSize;
  }
}
