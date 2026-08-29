// @ts-nocheck
import type { Type } from '@nestjs/common';
import { ConnectionOptions } from 'tls';
import { ClientProxy } from '../client/index.js';
import { Transport } from '../enums/transport.enum.js';
import { TcpSocket } from '../helpers/index.js';
import { Deserializer } from './deserializer.interface.js';
import {
  GrpcOptions,
  KafkaOptions,
  MqttOptions,
  NatsOptions,
  RedisOptions,
  RmqOptions,
} from './microservice-configuration.interface.js';
import { Serializer } from './serializer.interface.js';

export type ClientOptions =
  | RedisOptions
  | NatsOptions
  | MqttOptions
  | GrpcOptions
  | KafkaOptions
  | TcpClientOptions
  | RmqOptions;

export interface CustomClientOptions {
  customClass: Type<ClientProxy>;
  options?: Record<string, any>;
}

export interface TcpClientOptions {
  transport: Transport.TCP;
  options?: {
    host?: string;
    port?: number;
    serializer?: Serializer;
    deserializer?: Deserializer;
    tlsOptions?: ConnectionOptions;
    socketClass?: Type<TcpSocket>;
    maxBufferSize?: number;
  };
}
