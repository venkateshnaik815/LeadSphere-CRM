// @ts-nocheck
import { ContextId, HostComponentInfo } from '../injector/instance-wrapper.js';
import { REQUEST_CONTEXT_ID } from '../router/request/request-constants.js';
import { isObject } from '@nestjs/common/internal';

export function createContextId(): ContextId {
  return { id: Math.random() };
}

export type ContextIdResolverFn = (info: HostComponentInfo) => ContextId;

export interface ContextIdResolver {
  payload: unknown;
  resolve: ContextIdResolverFn;
}

export interface ContextIdStrategy<T = any> {
  attach(
    contextId: ContextId,
    request: T,
  ): ContextIdResolverFn | ContextIdResolver | undefined;
}

export class ContextIdFactory {
  private static strategy?: ContextIdStrategy;

  public static create(): ContextId {
    return createContextId();
  }

  public static getByRequest<T extends Record<any, any> = any>(
    request: T,
    propsToInspect: string[] = ['raw'],
  ): ContextId {
    if (!request) {
      return ContextIdFactory.create();
    }
    if (request[REQUEST_CONTEXT_ID as any]) {
      return request[REQUEST_CONTEXT_ID as any];
    }
    for (const key of propsToInspect) {
      if (request[key]?.[REQUEST_CONTEXT_ID]) {
        return request[key][REQUEST_CONTEXT_ID];
      }
    }
    if (!this.strategy) {
      return ContextIdFactory.create();
    }
    const contextId = createContextId();
    const resolverObjectOrFunction = this.strategy.attach(contextId, request);
    if (this.isContextIdResolverWithPayload(resolverObjectOrFunction!)) {
      contextId.getParent = resolverObjectOrFunction.resolve;
      contextId.payload = resolverObjectOrFunction.payload;
    } else {
      contextId.getParent = resolverObjectOrFunction;
    }
    return contextId;
  }

  public static apply(strategy: ContextIdStrategy) {
    this.strategy = strategy;
  }

  private static isContextIdResolverWithPayload(
    resolverOrResolverFn: ContextIdResolver | ContextIdResolverFn,
  ): resolverOrResolverFn is ContextIdResolver {
    return isObject(resolverOrResolverFn);
  }
}
