// @ts-nocheck
import { HEADERS_METADATA } from '../../constants.js';
import { extendArrayMetadata } from '../../utils/extend-metadata.util.js';

export function Header(
  name: string,
  value: string | (() => string),
): MethodDecorator {
  return (
    target: object,
    key: string | symbol,
    descriptor: TypedPropertyDescriptor<any>,
  ) => {
    extendArrayMetadata(HEADERS_METADATA, [{ name, value }], descriptor.value);
    return descriptor;
  };
}
