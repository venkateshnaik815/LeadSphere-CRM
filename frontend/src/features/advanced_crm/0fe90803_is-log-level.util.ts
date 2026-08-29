// @ts-nocheck
import { LOG_LEVELS, LogLevel } from '../logger.service.js';

export function isLogLevel(maybeLogLevel: any): maybeLogLevel is LogLevel {
  return LOG_LEVELS.includes(maybeLogLevel);
}
