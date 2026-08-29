// @ts-nocheck
import { TestingModuleBuilder } from '../testing-module.builder.js';
import type { ModuleDefinition } from '@nestjs/core/internal';

export interface OverrideModule {
  useModule: (newModule: ModuleDefinition) => TestingModuleBuilder;
}
