// @ts-nocheck
import type { OnApplicationBootstrap } from '@nestjs/common';
import { isFunction, isNil } from '@nestjs/common/internal';
import { iterate } from 'iterare';
import { Module } from '../injector/module.js';
import { getInstancesGroupedByHierarchyLevel } from './utils/get-instances-grouped-by-hierarchy-level.js';
import { getSortedHierarchyLevels } from './utils/get-sorted-hierarchy-levels.js';

function hasOnAppBootstrapHook(
  instance: unknown,
): instance is OnApplicationBootstrap {
  return isFunction(
    (instance as OnApplicationBootstrap).onApplicationBootstrap,
  );
}

function callOperator(instances: unknown[]): Promise<any>[] {
  return iterate(instances)
    .filter(instance => !isNil(instance))
    .filter(hasOnAppBootstrapHook)
    .map(async instance =>
      (instance as any as OnApplicationBootstrap).onApplicationBootstrap(),
    )
    .toArray();
}

export async function callModuleBootstrapHook(moduleRef: Module): Promise<any> {
  const providers = moduleRef.getNonAliasProviders();
  // Module (class) instance is the first element of the providers array
  // Lifecycle hook has to be called once all classes are properly initialized
  const [_, moduleClassHost] = providers.shift()!;
  const groupedInstances = getInstancesGroupedByHierarchyLevel(
    moduleRef.controllers,
    moduleRef.injectables,
    moduleRef.middlewares,
    providers,
  );

  const levels = getSortedHierarchyLevels(groupedInstances);
  for (const level of levels) {
    await Promise.all(callOperator(groupedInstances.get(level)!));
  }

  // Call the instance itself
  const moduleClassInstance = moduleClassHost.instance;
  if (
    moduleClassInstance &&
    hasOnAppBootstrapHook(moduleClassInstance) &&
    moduleClassHost.isDependencyTreeStatic()
  ) {
    await moduleClassInstance.onApplicationBootstrap();
  }
}
