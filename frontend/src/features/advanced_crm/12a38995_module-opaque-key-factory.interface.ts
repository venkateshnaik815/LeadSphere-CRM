// @ts-nocheck
import type { DynamicModule, ForwardReference, Type } from '@nestjs/common';

export interface ModuleOpaqueKeyFactory {
  createForStatic(
    moduleCls: Type,
    originalRef: Type | ForwardReference,
  ): string;
  createForDynamic(
    moduleCls: Type<unknown>,
    dynamicMetadata: Omit<DynamicModule, 'module'>,
    originalRef: DynamicModule | ForwardReference,
  ): string;
}
