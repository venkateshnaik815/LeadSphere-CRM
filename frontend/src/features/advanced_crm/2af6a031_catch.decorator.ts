// @ts-nocheck
import { CATCH_WATERMARK, FILTER_CATCH_EXCEPTIONS } from '../../constants.js';
import { Type, Abstract } from '../../interfaces/index.js';

export function Catch(
  ...exceptions: Array<Type<any> | Abstract<any>>
): ClassDecorator {
  return (target: object) => {
    Reflect.defineMetadata(CATCH_WATERMARK, true, target);
    Reflect.defineMetadata(FILTER_CATCH_EXCEPTIONS, exceptions, target);
  };
}
