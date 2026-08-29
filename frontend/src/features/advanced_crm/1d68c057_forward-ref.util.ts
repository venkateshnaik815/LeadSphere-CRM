// @ts-nocheck
import { ForwardReference } from '../interfaces/modules/forward-reference.interface.js';

export const forwardRef = (fn: () => any): ForwardReference => ({
  forwardRef: fn,
});
