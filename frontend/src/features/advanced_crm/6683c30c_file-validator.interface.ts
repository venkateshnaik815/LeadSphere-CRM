// @ts-nocheck
import { IFile } from './interfaces/index.js';

export abstract class FileValidator<
  TValidationOptions = Record<string, any>,
  TFile extends IFile = IFile,
> {
  constructor(protected readonly validationOptions: TValidationOptions) {}

  abstract isValid(
    file?: TFile | TFile[] | Record<string, TFile[]>,
  ): boolean | Promise<boolean>;

  abstract buildErrorMessage(file: any): string;
}
