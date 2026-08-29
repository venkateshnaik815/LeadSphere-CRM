// @ts-nocheck
import { RpcException } from './rpc-exception.js';

export class KafkaRetriableException extends RpcException {
  public getError(): string | object {
    return this;
  }
}
