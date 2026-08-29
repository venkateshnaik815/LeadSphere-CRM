// @ts-nocheck
import { TransportId } from './microservice-configuration.interface.js';

export interface CustomTransportStrategy {
  transportId?: TransportId;
  listen(callback: (...optionalParams: unknown[]) => any): any;
  close(): any;
}
