// @ts-nocheck
type VoidCallback = () => void;
type OnErrorCallback = (error: Error) => void;
type OnBlockedCallback = (arg: { reason: string }) => void;

export const enum RmqStatus {
  DISCONNECTED = 'disconnected',
  CONNECTED = 'connected',
  BLOCKED = 'blocked',
  UNBLOCKED = 'unblocked',
}

export const enum RmqEventsMap {
  ERROR = 'error',
  DISCONNECT = 'disconnect',
  CONNECT = 'connect',
  BLOCKED = 'blocked',
  UNBLOCKED = 'unblocked',
}

export type RmqEvents = {
  error: OnErrorCallback;
  disconnect: VoidCallback;
  connect: VoidCallback;
  blocked: OnBlockedCallback;
  unblocked: VoidCallback;
};
