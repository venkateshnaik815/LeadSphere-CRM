// @ts-nocheck
import type { EnhancerSubtype } from '@nestjs/common/internal';

export interface AttachedEnhancerDefinition {
  nodeId: string;
}

export interface OrphanedEnhancerDefinition {
  subtype: EnhancerSubtype;
  ref: unknown;
}

export interface Extras {
  orphanedEnhancers: Array<OrphanedEnhancerDefinition>;
  attachedEnhancers: Array<AttachedEnhancerDefinition>;
}
