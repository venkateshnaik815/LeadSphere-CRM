// @ts-nocheck
export interface ITransportServer {
  listen(callback: (...optionalParams: unknown[]) => any): any;

  close(): any;
}
