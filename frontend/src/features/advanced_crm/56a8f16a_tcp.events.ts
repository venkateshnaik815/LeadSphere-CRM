// @ts-nocheck
type VoidCallback = () => void;
type OnErrorCallback = (error: Error) => void;
type OnLookupCallback = (
  err: Error,
  address: string,
  family: string,
  host: string,
) => void;

export const enum TcpStatus {
  DISCONNECTED = 'disconnected',
  CONNECTED = 'connected',
}

export const enum TcpEventsMap {
  ERROR = 'error',
  CONNECT = 'connect',
  END = 'end',
  CLOSE = 'close',
  TIMEOUT = 'timeout',
  DRAIN = 'drain',
  LOOKUP = 'lookup',
  LISTENING = 'listening',
}

export type TcpEvents = {
  error: OnErrorCallback;
  connect: VoidCallback;
  end: VoidCallback;
  close: VoidCallback;
  timeout: VoidCallback;
  drain: VoidCallback;
  lookup: OnLookupCallback;
};
