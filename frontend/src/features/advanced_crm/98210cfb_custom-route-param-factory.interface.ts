// @ts-nocheck
import { ExecutionContext } from './execution-context.interface.js';

export type CustomParamFactory<TData = any, TOutput = any> = (
  data: TData,
  context: ExecutionContext,
) => TOutput;
