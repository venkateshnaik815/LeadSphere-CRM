// @ts-nocheck
import { ConnectionOptions } from 'tls';

export interface IORedisOptions {
  Connector?: any;
  retryStrategy?: (times: number) => number | void | null;

  commandTimeout?: number;
  keepAlive?: number;

  noDelay?: boolean;

  connectionName?: string;

  clientInfoTag?: string;

  username?: string;

  password?: string;

  db?: number;

  autoResubscribe?: boolean;

  autoResendUnfulfilledCommands?: boolean;
  reconnectOnError?: ((err: Error) => boolean | 1 | 2) | null;

  readOnly?: boolean;
  stringNumbers?: boolean;

  connectTimeout?: number;

  monitor?: boolean;

  maxRetriesPerRequest?: number | null;

  maxLoadingRetryTime?: number;
  enableAutoPipelining?: boolean;
  autoPipeliningIgnoredCommands?: string[];
  offlineQueue?: boolean;
  commandQueue?: boolean;

  enableOfflineQueue?: boolean;

  enableReadyCheck?: boolean;


  lazyConnect?: boolean;

  scripts?: Record<
    string,
    { lua: string; numberOfKeys?: number; readOnly?: boolean }
  >;

  keyPrefix?: string;
  showFriendlyErrorStack?: boolean;

  // StandaloneConnectionOptions
  disconnectTimeout?: number;
  tls?: ConnectionOptions;

  // SentinelConnectionOptions
  name?: string;
  role?: 'master' | 'slave';
  sentinelUsername?: string;
  sentinelPassword?: string;
  sentinels?: Array<Partial<any>>;
  sentinelRetryStrategy?: (retryAttempts: number) => number | void | null;
  sentinelReconnectStrategy?: (retryAttempts: number) => number | void | null;
  preferredSlaves?: any;
  sentinelCommandTimeout?: number;
  enableTLSForSentinelMode?: boolean;
  sentinelTLS?: ConnectionOptions;
  natMap?: any;
  updateSentinels?: boolean;
  sentinelMaxConnections?: number;
  failoverDetector?: boolean;
}
