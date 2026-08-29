// @ts-nocheck
import type { Logger } from '@nestjs/common';
import { ReplContext } from './repl-context.js';
import type { ReplFnDefinition } from './repl.interfaces.js';
import { clc } from '@nestjs/common/internal';

export abstract class ReplFunction<
  ActionParams extends Array<unknown> = Array<unknown>,
  ActionReturn = any,
> {
  public abstract fnDefinition: ReplFnDefinition;

  protected readonly logger: Logger;

  constructor(protected readonly ctx: ReplContext) {
    this.logger = ctx.logger;
  }

  abstract action(...args: ActionParams): ActionReturn;

  public makeHelpMessage(): string {
    const { description, name, signature } = this.fnDefinition;

    const fnSignatureWithName = `${name}${signature}`;

    return `${clc.yellow(description)}\n${clc.magentaBright(
      'Interface:',
    )} ${clc.bold(fnSignatureWithName)}\n`;
  }
}
