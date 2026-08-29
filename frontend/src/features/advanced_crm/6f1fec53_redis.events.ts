// @ts-nocheck
type VoidCallback = (client: 'pub' | 'sub') => void;
type OnErrorCallback = (client: 'pub' | 'sub', error: Error) => void;
type OnWarningCallback = (client: 'pub' | 'sub', warning: any) => void;

export const enum RedisStatus {
  DISCONNECTED = 'disconnected',
  RECONNECTING = 'reconnecting',
  CONNECTED = 'connected',
}

export const enum RedisEventsMap {
  CONNECT = 'connect',
  READY = 'ready',
  ERROR = 'error',
  CLOSE = 'close',
  RECONNECTING = 'reconnecting',
  END = 'end',
  WARNING = 'warning',
}

export type RedisEvents = {
  connect: VoidCallback;
  ready: VoidCallback;
  error: OnErrorCallback;
  close: VoidCallback;
  reconnecting: VoidCallback;
  end: VoidCallback;
  warning: OnWarningCallback;
};
