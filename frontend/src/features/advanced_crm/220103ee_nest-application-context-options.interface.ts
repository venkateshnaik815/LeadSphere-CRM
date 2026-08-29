// @ts-nocheck
import { LoggerService, LogLevel } from '../services/logger.service.js';

export class NestApplicationContextOptions {
  logger?: LoggerService | LogLevel[] | false;

  abortOnError?: boolean | undefined;

  bufferLogs?: boolean;

  autoFlushLogs?: boolean;

  preview?: boolean;

  snapshot?: boolean;

  moduleIdGeneratorAlgorithm?: 'deep-hash' | 'reference';

  instrument?: {
    instanceDecorator: (instance: unknown) => unknown;
  };

  forceConsole?: boolean;
}
