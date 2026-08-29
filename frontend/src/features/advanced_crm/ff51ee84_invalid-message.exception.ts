// @ts-nocheck
import { RuntimeException } from '@nestjs/core/internal';

export class InvalidMessageException extends RuntimeException {
  constructor() {
    super(`The invalid data or message pattern (undefined/null)`);
  }
}
