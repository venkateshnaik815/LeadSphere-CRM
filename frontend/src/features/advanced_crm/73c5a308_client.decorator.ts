// @ts-nocheck
import {
  CLIENT_CONFIGURATION_METADATA,
  CLIENT_METADATA,
} from '../constants.js';
import { ClientOptions } from '../interfaces/client-metadata.interface.js';

export function Client(metadata?: ClientOptions): PropertyDecorator {
  return (target: object, propertyKey: string | symbol): void => {
    Reflect.set(target, propertyKey, null);
    Reflect.defineMetadata(CLIENT_METADATA, true, target, propertyKey);
    Reflect.defineMetadata(
      CLIENT_CONFIGURATION_METADATA,
      metadata,
      target,
      propertyKey,
    );
  };
}
