// @ts-nocheck
import type { ReplContext } from './repl-context.js';
import type { ReplFunction } from './repl-function.js';

export type ReplFnDefinition = {
  name: string;

  aliases?: ReplFnDefinition['name'][];

  description: string;

  signature: string;
};

export type ReplFunctionClass = new (replContext: ReplContext) => ReplFunction;
