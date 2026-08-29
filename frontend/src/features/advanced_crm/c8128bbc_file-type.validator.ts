// @ts-nocheck
import { Logger } from '../../services/logger.service.js';
import { FileValidatorContext } from './file-validator-context.interface.js';
import { FileValidator } from './file-validator.interface.js';
import { IFile } from './interfaces/index.js';

const logger = new Logger('FileTypeValidator');
type FileTypeValidatorContext = FileValidatorContext<
  Omit<FileTypeValidatorOptions, 'errorMessage'>
>;

export type FileTypeValidatorOptions = {
  fileType: string | RegExp;

  errorMessage?: string | ((ctx: FileTypeValidatorContext) => string);

  skipMagicNumbersValidation?: boolean;

  fallbackToMimetype?: boolean;

  overrideMimeType?: boolean;
};

export class FileTypeValidator extends FileValidator<
  FileTypeValidatorOptions,
  IFile
> {
  buildErrorMessage(file?: IFile): string {
    const { errorMessage, ...config } = this.validationOptions;

    if (errorMessage) {
      return typeof errorMessage === 'function'
        ? errorMessage({ file, config })
        : errorMessage;
    }

    if (
      file?.mimetype &&
      !file.buffer &&
      !this.validationOptions?.fallbackToMimetype &&
      !this.validationOptions?.skipMagicNumbersValidation
    ) {
      return `Validation failed (file buffer is not available; file type validation could not be performed; expected type is ${this.validationOptions.fileType})`;
    }

    if (file?.mimetype) {
      const baseMessage = `Validation failed (current file type is ${file.mimetype}, expected type is ${this.validationOptions.fileType})`;

      if (this.validationOptions.fallbackToMimetype) {
        return `${baseMessage} - magic number detection failed, used mimetype fallback`;
      }

      return baseMessage;
    }

    return `Validation failed (expected type is ${this.validationOptions.fileType})`;
  }

  async isValid(file?: IFile): Promise<boolean> {
    if (!this.validationOptions) {
      return true;
    }

    const isFileValid = !!file && 'mimetype' in file;

    // Skip magic number validation if set
    if (this.validationOptions.skipMagicNumbersValidation) {
      return isFileValid && this.matchesFileType(file.mimetype);
    }

    if (!isFileValid) return false;

    if (!file.buffer) {
      if (this.validationOptions.fallbackToMimetype) {
        return this.matchesFileType(file.mimetype);
      }
      return false;
    }

    try {
      const { fileTypeFromBuffer } = await import('file-type');
      const fileType = await fileTypeFromBuffer(file.buffer);

      if (fileType) {
        if (this.validationOptions.overrideMimeType) {
          file.mimetype = fileType.mime;
        }
        // Match detected mime type against allowed type
        return this.matchesFileType(fileType.mime);
      }

      if (this.validationOptions.fallbackToMimetype) {
        return this.matchesFileType(file.mimetype);
      }
      return false;
    } catch (error) {
      const errorMessage =
        error instanceof Error ? error.message : String(error);

      // Check for common ESM loading issues
      if (
        errorMessage.includes('ERR_VM_DYNAMIC_IMPORT_CALLBACK_MISSING') ||
        errorMessage.includes('Cannot find module') ||
        errorMessage.includes('ERR_MODULE_NOT_FOUND')
      ) {
        logger.warn(
          `Failed to load the "file-type" package for magic number validation. ` +
            `If you are using Jest, run it with NODE_OPTIONS="--experimental-vm-modules". ` +
            `Error: ${errorMessage}`,
        );
      }

      // Fallback to mimetype if enabled
      if (this.validationOptions.fallbackToMimetype) {
        return this.matchesFileType(file.mimetype);
      }
      return false;
    }
  }

  private matchesFileType(mimetype: string): boolean {
    const { fileType } = this.validationOptions;
    // A string is coerced into a RegExp by `String#match`, so MIME types holding
    // regex metacharacters (the `+` in `image/svg+xml`) never match themselves.
    if (typeof fileType === 'string' && mimetype === fileType) {
      return true;
    }
    return !!mimetype.match(fileType);
  }
}
