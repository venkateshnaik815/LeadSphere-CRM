// @ts-nocheck
import { Type } from '../type.interface.js';
import { Paramtype } from './paramtype.interface.js';
import type { StandardSchemaV1 } from '@standard-schema/spec';

export type Transform<T = any> = (value: T, metadata: ArgumentMetadata) => any;

export interface ArgumentMetadata<Metatype = any> {
  readonly type: Paramtype;
  readonly metatype?: Type<Metatype> | undefined;
  readonly data?: string | undefined;
  readonly schema?: StandardSchemaV1;
}

export interface PipeTransform<T = any, R = any> {
  transform(value: T, metadata: ArgumentMetadata): R;
}
