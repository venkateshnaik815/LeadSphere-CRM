// @ts-nocheck
import { ClientProxy } from '../client/index.js';
import { KafkaStatus } from '../events/index.js';
import {
  Consumer,
  Producer,
  TopicPartitionOffsetAndMetadata,
} from '../external/kafka.interface.js';

export interface ClientKafkaProxy extends Omit<
  ClientProxy<never, KafkaStatus>,
  'on'
> {
  consumer: Consumer | null;
  producer: Producer | null;
  subscribeToResponseOf(pattern: unknown): void;
  commitOffsets(
    topicPartitions: TopicPartitionOffsetAndMetadata[],
  ): Promise<void>;
}
