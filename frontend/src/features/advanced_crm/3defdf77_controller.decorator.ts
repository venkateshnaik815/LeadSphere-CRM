// @ts-nocheck
import {
  CONTROLLER_WATERMARK,
  HOST_METADATA,
  PATH_METADATA,
  SCOPE_OPTIONS_METADATA,
  VERSION_METADATA,
} from '../../constants.js';
import { ScopeOptions, VersionOptions } from '../../interfaces/index.js';
import { isString, isUndefined } from '../../utils/shared.utils.js';

export interface ControllerOptions extends ScopeOptions, VersionOptions {
  path?: string | string[];

  host?: string | RegExp | Array<string | RegExp>;
}

export function Controller(): ClassDecorator;

export function Controller(prefix: string | string[]): ClassDecorator;

export function Controller(options: ControllerOptions): ClassDecorator;

export function Controller(
  prefixOrOptions?: string | string[] | ControllerOptions,
): ClassDecorator {
  const defaultPath = '/';

  const [path, host, scopeOptions, versionOptions] = isUndefined(
    prefixOrOptions,
  )
    ? [defaultPath, undefined, undefined, undefined]
    : isString(prefixOrOptions) || Array.isArray(prefixOrOptions)
      ? [prefixOrOptions, undefined, undefined, undefined]
      : [
          prefixOrOptions.path || defaultPath,
          prefixOrOptions.host,
          { scope: prefixOrOptions.scope, durable: prefixOrOptions.durable },
          Array.isArray(prefixOrOptions.version)
            ? Array.from(new Set(prefixOrOptions.version))
            : prefixOrOptions.version,
        ];

  return (target: object) => {
    Reflect.defineMetadata(CONTROLLER_WATERMARK, true, target);
    Reflect.defineMetadata(PATH_METADATA, path, target);
    Reflect.defineMetadata(HOST_METADATA, host, target);
    Reflect.defineMetadata(SCOPE_OPTIONS_METADATA, scopeOptions, target);
    Reflect.defineMetadata(VERSION_METADATA, versionOptions, target);
  };
}
