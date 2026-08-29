// @ts-nocheck
import { type CustomDecorator, SetMetadata, type Type } from '@nestjs/common';
import { uid } from 'uid';
import { isEmptyArray, isObject } from '@nestjs/common/internal';

export interface CreateDecoratorOptions<TParam = any, TTransformed = TParam> {
  key?: string;

  transform?: (value: TParam) => TTransformed;
}

type CreateDecoratorWithTransformOptions<
  TParam,
  TTransformed = TParam,
> = CreateDecoratorOptions<TParam, TTransformed> &
  Required<Pick<CreateDecoratorOptions<TParam, TTransformed>, 'transform'>>;

export type ReflectableDecorator<TParam, TTransformed = TParam> = ((
  opts?: TParam,
) => CustomDecorator) & {
  KEY: string;
};

export class Reflector {
  static createDecorator<TParam>(
    options?: CreateDecoratorOptions<TParam>,
  ): ReflectableDecorator<TParam>;
  static createDecorator<TParam, TTransformed>(
    options: CreateDecoratorWithTransformOptions<TParam, TTransformed>,
  ): ReflectableDecorator<TParam, TTransformed>;
  static createDecorator<TParam, TTransformed = TParam>(
    options: CreateDecoratorOptions<TParam, TTransformed> = {},
  ): ReflectableDecorator<TParam, TTransformed> {
    const metadataKey = options.key ?? uid(21);
    const decoratorFn =
      (metadataValue: TParam) =>
      (target: object | Function, key?: string | symbol, descriptor?: any) => {
        const value = options.transform
          ? options.transform(metadataValue)
          : metadataValue;
        SetMetadata(metadataKey, value ?? {})(target, key!, descriptor);
      };

    decoratorFn.KEY = metadataKey;
    return decoratorFn as ReflectableDecorator<TParam, TTransformed>;
  }

  public get<T extends ReflectableDecorator<any>>(
    decorator: T,
    target: Type<any> | Function,
  ): T extends ReflectableDecorator<any, infer R> ? R : unknown;
  public get<TResult = any, TKey = any>(
    metadataKey: TKey,
    target: Type<any> | Function,
  ): TResult;
  public get<TResult = any, TKey = any>(
    metadataKeyOrDecorator: TKey,
    target: Type<any> | Function,
  ): TResult {
    const metadataKey =
      (metadataKeyOrDecorator as ReflectableDecorator<unknown>).KEY ??
      metadataKeyOrDecorator;

    return Reflect.getMetadata(metadataKey, target);
  }

  public getAll<TParam = any, TTransformed = TParam>(
    decorator: ReflectableDecorator<TParam, TTransformed>,
    targets: (Type<any> | Function)[],
  ): TTransformed extends Array<any> ? TTransformed : TTransformed[];
  public getAll<TResult extends any[] = any[], TKey = any>(
    metadataKey: TKey,
    targets: (Type<any> | Function)[],
  ): TResult;
  public getAll<TResult extends any[] = any[], TKey = any>(
    metadataKeyOrDecorator: TKey,
    targets: (Type<any> | Function)[],
  ): TResult {
    return (targets || []).map(target =>
      this.get(metadataKeyOrDecorator, target),
    ) as TResult;
  }

  public getAllAndMerge<TParam = any, TTransformed = TParam>(
    decorator: ReflectableDecorator<TParam, TTransformed>,
    targets: (Type<any> | Function)[],
  ): TTransformed extends Array<any>
    ? TTransformed
    : TTransformed extends object
      ? TTransformed
      : TTransformed[];
  public getAllAndMerge<TResult extends any[] | object = any[], TKey = any>(
    metadataKey: TKey,
    targets: (Type<any> | Function)[],
  ): TResult;
  public getAllAndMerge<TResult extends any[] | object = any[], TKey = any>(
    metadataKeyOrDecorator: TKey,
    targets: (Type<any> | Function)[],
  ): TResult {
    const metadataCollection = this.getAll<any[], TKey>(
      metadataKeyOrDecorator,
      targets,
    ).filter(item => item !== undefined);

    if (isEmptyArray(metadataCollection)) {
      return metadataCollection as TResult;
    }
    if (metadataCollection.length === 1) {
      const value = metadataCollection[0];
      if (isObject(value)) {
        return value as TResult;
      }
      return metadataCollection as TResult;
    }
    return metadataCollection.reduce((a, b) => {
      if (Array.isArray(a)) {
        return a.concat(b);
      }
      if (isObject(a) && isObject(b)) {
        return {
          ...a,
          ...b,
        };
      }
      return [a, b];
    });
  }

  public getAllAndOverride<TParam = any, TTransformed = TParam>(
    decorator: ReflectableDecorator<TParam, TTransformed>,
    targets: (Type<any> | Function)[],
  ): TTransformed;
  public getAllAndOverride<TResult = any, TKey = any>(
    metadataKey: TKey,
    targets: (Type<any> | Function)[],
  ): TResult;
  public getAllAndOverride<TResult = any, TKey = any>(
    metadataKeyOrDecorator: TKey,
    targets: (Type<any> | Function)[],
  ): TResult | undefined {
    for (const target of targets) {
      const result = this.get(metadataKeyOrDecorator, target);
      if (result !== undefined) {
        return result;
      }
    }
    return undefined;
  }
}
