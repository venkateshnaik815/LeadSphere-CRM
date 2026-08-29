// @ts-nocheck
import { Logger } from '@nestjs/common';
import type { OnApplicationShutdown } from '@nestjs/common';
import { isFunction, isNil } from '@nestjs/common/internal';
import { iterate } from 'iterare';
import { Module } from '../injector/module.js';
import { getInstancesGroupedByHierarchyLevel } from './utils/get-instances-grouped-by-hierarchy-level.js';
import { getSortedHierarchyLevels } from './utils/get-sorted-hierarchy-levels.js';

function hasOnAppShutdownHook(
  instance: unknown,
): instance is OnApplicationShutdown {
  return isFunction((instance as OnApplicationShutdown).onApplicationShutdown);
}

function callOperator(instances: unknown[], signal?: string): Promise<any>[] {
  return iterate(instances)
    .filter(instance => !isNil(instance))
    .filter(hasOnAppShutdownHook)
    .map(async instance =>
      (instance as any as OnApplicationShutdown).onApplicationShutdown(signal),
    )
    .toArray();
}

export async function callAppShutdownHook(
  moduleRef: Module,
  signal?: string,
): Promise<any> {
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

  const levels = getSortedHierarchyLevels(groupedInstances, 'DESC');
  for (const level of levels) {
    const results = await Promise.allSettled(
      callOperator(groupedInstances.get(level)!, signal),
    );
    results
      .filter(
        (result): result is PromiseRejectedResult =>
          result.status === 'rejected',
      )
      .forEach(result =>
        Logger.error(result.reason, (result.reason as Error)?.stack),
      );
  }
  // Call the instance itself
  const moduleClassInstance = moduleClassHost.instance;
  if (
    moduleClassInstance &&
    hasOnAppShutdownHook(moduleClassInstance) &&
    moduleClassHost.isDependencyTreeStatic()
  ) {
    try {
      await moduleClassInstance.onApplicationShutdown(signal);
    } catch (err) {
      Logger.error(err, (err as Error)?.stack);
    }
  }
}
