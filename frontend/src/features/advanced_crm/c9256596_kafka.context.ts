// @ts-nocheck
import {
  Consumer,
  KafkaMessage,
  Producer,
} from '../external/kafka.interface.js';
import { BaseRpcContext } from './base-rpc.context.js';

type KafkaContextArgs = [
  message: KafkaMessage,
  partition: number,
  topic: string,
  consumer: Consumer,
  heartbeat: () => Promise<void>,
  producer: Producer,
];

export class KafkaContext extends BaseRpcContext<KafkaContextArgs> {
  constructor(args: KafkaContextArgs) {
    super(args);
  }

  getMessage() {
    return this.args[0];
  }

  getPartition() {
    return this.args[1];
  }

  getTopic() {
    return this.args[2];
  }

  getConsumer() {
    return this.args[3];
  }

  getHeartbeat() {
    return this.args[4];
  }

  getProducer() {
    return this.args[5];
  }
}
