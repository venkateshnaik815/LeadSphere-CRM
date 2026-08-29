// @ts-nocheck
export type ContextType = 'http' | 'ws' | 'rpc';

export interface HttpArgumentsHost {
  getRequest<T = any>(): T;
  getResponse<T = any>(): T;
  getNext<T = any>(): T;
}

export interface WsArgumentsHost {
  getData<T = any>(): T;
  getClient<T = any>(): T;
  getPattern(): string;
}

export interface RpcArgumentsHost {
  getData<T = any>(): T;

  getContext<T = any>(): T;
}

export interface ArgumentsHost {
  getArgs<T extends Array<any> = any[]>(): T;
  getArgByIndex<T = any>(index: number): T;
  switchToRpc(): RpcArgumentsHost;
  switchToHttp(): HttpArgumentsHost;
  switchToWs(): WsArgumentsHost;
  getType<TContext extends string = ContextType>(): TContext;
}
