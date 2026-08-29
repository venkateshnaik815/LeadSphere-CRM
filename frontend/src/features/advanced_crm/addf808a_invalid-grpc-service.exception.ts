// @ts-nocheck
import { RuntimeException } from '@nestjs/core/internal';

export class InvalidGrpcServiceException extends RuntimeException {
  constructor(name: string) {
    super(`The invalid gRPC service (service "${name}" not found)`);
  }
}
