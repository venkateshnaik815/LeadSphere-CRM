// @ts-nocheck
import { Provider, Scope } from '@nestjs/common';
import { REQUEST } from '@nestjs/core';
import { TenantContext } from './durable-context-id.strategy.js';

export const TENANT_CONNECTION = 'TENANT_CONNECTION';

export interface TenantConnection {
  tenantId: string;
}

export const tenantConnectionProvider: Provider = {
  provide: TENANT_CONNECTION,
  scope: Scope.REQUEST,
  durable: true,
  useFactory: (payload: TenantContext): TenantConnection => ({
    tenantId: payload.tenantId,
  }),
  inject: [REQUEST],
};
