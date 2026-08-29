// @ts-nocheck
import { BaseRpcContext } from './base-rpc.context.js';

type RedisContextArgs = [string];

export class RedisContext extends BaseRpcContext<RedisContextArgs> {
  constructor(args: RedisContextArgs) {
    super(args);
  }

  getChannel() {
    return this.args[0];
  }
}
