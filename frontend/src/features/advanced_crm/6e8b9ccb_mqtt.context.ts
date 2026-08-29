// @ts-nocheck
import { BaseRpcContext } from './base-rpc.context.js';

type MqttContextArgs = [string, Record<string, any>];

export class MqttContext extends BaseRpcContext<MqttContextArgs> {
  constructor(args: MqttContextArgs) {
    super(args);
  }

  getTopic() {
    return this.args[0];
  }

  getPacket() {
    return this.args[1];
  }
}
