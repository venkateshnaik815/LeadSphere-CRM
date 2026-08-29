// @ts-nocheck
import { RuntimeException } from '@nestjs/core/internal';

export class InvalidProtoDefinitionException extends RuntimeException {
  constructor(path: string) {
    super(`The invalid .proto definition (file at "${path}" not found)`);
  }
}
