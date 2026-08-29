// @ts-nocheck
import { forwardRef, Inject, Injectable } from '@nestjs/common';
import { DurableLionsService } from './durable-lions.service.js';
import {
  TENANT_CONNECTION,
  TenantConnection,
} from './durable-tenant-connection.provider.js';

@Injectable()
export class DurableElephantsService {
  constructor(
    @Inject(TENANT_CONNECTION)
    private readonly connection: TenantConnection,
    @Inject(forwardRef(() => DurableLionsService))
    private readonly lionsService: DurableLionsService,
  ) {}

  trumpet() {
    return `trumpet from ${this.connection.tenantId}, lion says ${this.lionsService.roar()}`;
  }
}
