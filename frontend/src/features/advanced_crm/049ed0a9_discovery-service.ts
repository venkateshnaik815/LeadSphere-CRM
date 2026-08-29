// @ts-nocheck
import {
  type CustomDecorator,
  flatten,
  Injectable,
  SetMetadata,
} from '@nestjs/common';
import { uid } from 'uid';
import { InstanceWrapper } from '../injector/instance-wrapper.js';
import { Module } from '../injector/module.js';
import { ModulesContainer } from '../injector/modules-container.js';
import { DiscoverableMetaHostCollection } from './discoverable-meta-host-collection.js';

export interface FilterByInclude {
  include?: Function[];
}

export interface FilterByMetadataKey {
  metadataKey?: string;
}

export type DiscoveryOptions = FilterByInclude | FilterByMetadataKey;

export type DiscoverableDecorator<T> = ((opts?: T) => CustomDecorator) & {
  KEY: string;
};

@Injectable()
export class DiscoveryService {
  constructor(private readonly modulesContainer: ModulesContainer) {}

  static createDecorator<T>(): DiscoverableDecorator<T> {
    const metadataKey = uid(21);
    const decoratorFn =
      (opts: T) =>
      (target: object | Function, key?: string | symbol, descriptor?: any) => {
        if (!descriptor) {
          DiscoverableMetaHostCollection.addClassMetaHostLink(
            target as Function,
            metadataKey,
          );
        }
        SetMetadata(metadataKey, opts ?? {})(target, key!, descriptor);
      };

    decoratorFn.KEY = metadataKey;
    return decoratorFn as DiscoverableDecorator<T>;
  }

  public getProviders(
    options: DiscoveryOptions = {},
    modules: Module[] = this.getModules(options),
  ): InstanceWrapper[] {
    if ('metadataKey' in options) {
      const providers = DiscoverableMetaHostCollection.getProvidersByMetaKey(
        this.modulesContainer,
        options.metadataKey!,
      );
      return Array.from(providers);
    }

    const providers = modules.map(item => [...item.providers.values()]);
    return flatten(providers);
  }

  public getControllers(
    options: DiscoveryOptions = {},
    modules: Module[] = this.getModules(options),
  ): InstanceWrapper[] {
    if ('metadataKey' in options) {
      const controllers =
        DiscoverableMetaHostCollection.getControllersByMetaKey(
          this.modulesContainer,
          options.metadataKey!,
        );
      return Array.from(controllers);
    }

    const controllers = modules.map(item => [...item.controllers.values()]);
    return flatten(controllers);
  }

  public getMetadataByDecorator<T extends DiscoverableDecorator<any>>(
    decorator: T,
    instanceWrapper: InstanceWrapper,
    methodKey?: string,
  ): T extends DiscoverableDecorator<infer R> ? R | undefined : T | undefined {
    if (methodKey) {
      return Reflect.getMetadata(
        decorator.KEY,
        instanceWrapper.instance[methodKey],
      );
    }

    const clsRef =
      instanceWrapper.instance?.constructor ?? instanceWrapper.metatype;
    return Reflect.getMetadata(decorator.KEY, clsRef);
  }

  protected getModules(options: DiscoveryOptions = {}): Module[] {
    const includeInOpts = 'include' in options;
    if (!includeInOpts) {
      const moduleRefs = [...this.modulesContainer.values()];
      return moduleRefs;
    }
    const whitelisted = this.includeWhitelisted(options.include!);
    return whitelisted;
  }

  private includeWhitelisted(include: Function[]): Module[] {
    const moduleRefs = [...this.modulesContainer.values()];
    return moduleRefs.filter(({ metatype }) =>
      include.some(item => item === metatype),
    );
  }
}
