// @ts-nocheck
export declare type QoS = 0 | 1 | 2;

export interface MqttClientOptions extends ISecureClientOptions {
  port?: number; // port is made into a number subsequently
  host?: string; // host does NOT include port
  hostname?: string;
  path?: string;
  protocol?: 'wss' | 'ws' | 'mqtt' | 'mqtts' | 'tcp' | 'ssl' | 'wx' | 'wxs';

  wsOptions?: {
    [x: string]: any;
  };
  keepalive?: number;
  clientId?: string;
  protocolId?: string;
  protocolVersion?: number;
  clean?: boolean;
  reconnectPeriod?: number;
  connectTimeout?: number;
  username?: string;
  password?: string;
  incomingStore?: any;
  outgoingStore?: any;
  queueQoSZero?: boolean;
  properties?: {
    sessionExpiryInterval?: number;
    receiveMaximum?: number;
    maximumPacketSize?: number;
    topicAliasMaximum?: number;
    requestResponseInformation?: boolean;
    requestProblemInformation?: boolean;
    userProperties?: object;
    authenticationMethod?: string;
    authenticationData?: any;
  };
  reschedulePings?: boolean;
  servers?: Array<{
    host: string;
    port: number;
  }>;
  resubscribe?: boolean;
  will?: {
    topic: string;
    payload: string;
    qos: QoS;
    retain: boolean;
  };
  transformWsUrl?: (url: string, options: any, client: any) => string;
}
export interface ISecureClientOptions {
  key?: string | string[] | Buffer | Buffer[] | Record<string, any>[];
  cert?: string | string[] | Buffer | Buffer[];
  ca?: string | string[] | Buffer | Buffer[];
  rejectUnauthorized?: boolean;
}
export interface IClientPublishOptions {
  qos: QoS;
  retain?: boolean;
  dup?: boolean;
}
export interface IClientSubscribeOptions {
  qos: QoS;
}
export interface IClientReconnectOptions {
  incomingStore?: any;
  outgoingStore?: any;
}
