// @ts-nocheck
export interface ClientGrpc {
  getService<T extends object>(name: string): T;
  getClientByServiceName<T = any>(name: string): T;
}
