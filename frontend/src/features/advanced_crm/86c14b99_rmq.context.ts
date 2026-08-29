// @ts-nocheck
import { BaseRpcContext } from './base-rpc.context.js';

type RmqContextArgs = [Record<string, any>, any, string];

export class RmqContext extends BaseRpcContext<RmqContextArgs> {
  constructor(args: RmqContextArgs) {
    super(args);
  }

  getMessage() {
    return this.args[0];
  }

  getChannelRef() {
    return this.args[1];
  }

  getPattern() {
    return this.args[2];
  }
}
