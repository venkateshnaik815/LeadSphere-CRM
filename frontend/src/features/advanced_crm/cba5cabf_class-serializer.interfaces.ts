// @ts-nocheck
import { ClassTransformOptions } from '../interfaces/external/class-transform-options.interface.js';
import { Type } from '../interfaces/index.js';

export interface ClassSerializerContextOptions extends ClassTransformOptions {
  type?: Type<any>;
}
