// @ts-nocheck
import { BaseRpcContext } from './base-rpc.context.js';

type NatsContextArgs = [string, any];

export class NatsContext extends BaseRpcContext<NatsContextArgs> {
  constructor(args: NatsContextArgs) {
    super(args);
  }

  getSubject() {
    return this.args[0];
  }

  getHeaders() {
    return this.args[1];
  }
}
