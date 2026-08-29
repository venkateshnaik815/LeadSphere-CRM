// @ts-nocheck
import { Injectable, Optional } from '../../decorators/core/index.js';
import { HttpStatus } from '../../enums/index.js';
import { PipeTransform } from '../../interfaces/features/pipe-transform.interface.js';
import { HttpErrorByCode } from '../../utils/http-error-by-code.util.js';
import { isEmptyArray, isNil, isObject } from '../../utils/shared.utils.js';
import { FileValidator } from './file-validator.interface.js';
import { ParseFileOptions } from './parse-file-options.interface.js';

@Injectable()
export class ParseFilePipe implements PipeTransform {
  protected exceptionFactory: (error: string) => any;
  private readonly validators: FileValidator[];
  private readonly fileIsRequired: boolean;

  constructor(@Optional() options: ParseFileOptions = {}) {
    const {
      exceptionFactory,
      errorHttpStatusCode = HttpStatus.BAD_REQUEST,
      validators = [],
      fileIsRequired,
    } = options;

    this.exceptionFactory =
      exceptionFactory ||
      (error => new HttpErrorByCode[errorHttpStatusCode](error));

    this.validators = validators;
    this.fileIsRequired = fileIsRequired ?? true;
  }

  async transform(value: unknown): Promise<any> {
    const areThereAnyFilesIn = this.thereAreNoFilesIn(value);

    if (areThereAnyFilesIn && this.fileIsRequired) {
      throw this.exceptionFactory('File is required');
    }
    if (!areThereAnyFilesIn && this.validators.length) {
      await this.validateFilesOrFile(value);
    }

    return value;
  }

  private async validateFilesOrFile(value: unknown): Promise<void> {
    if (Array.isArray(value)) {
      await Promise.all(value.map(f => this.validate(f)));
    } else {
      await this.validate(value);
    }
  }

  private thereAreNoFilesIn(value: unknown): boolean {
    const isEmptyObject = isObject(value) && isEmptyArray(Object.keys(value));
    return isNil(value) || isEmptyArray(value) || isEmptyObject;
  }

  protected async validate(file: unknown): Promise<any> {
    for (const validator of this.validators) {
      await this.validateOrThrow(file, validator);
    }
    return file;
  }

  private async validateOrThrow(file: unknown, validator: FileValidator) {
    const isValid = await validator.isValid(file as any);

    if (!isValid) {
      const errorMessage = validator.buildErrorMessage(file);
      throw this.exceptionFactory(errorMessage);
    }
  }

  getValidators() {
    return this.validators;
  }
}
