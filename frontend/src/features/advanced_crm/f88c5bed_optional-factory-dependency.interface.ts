// @ts-nocheck
import { InjectionToken } from './injection-token.interface.js';

export type OptionalFactoryDependency = {
  token: InjectionToken;
  optional: boolean;
};
