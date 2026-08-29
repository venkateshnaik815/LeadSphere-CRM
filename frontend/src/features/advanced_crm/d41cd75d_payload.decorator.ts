// @ts-nocheck
import type {
  ParameterDecoratorOptions,
  PipeTransform,
  Type,
} from '@nestjs/common';
import { RpcParamtype } from '../enums/rpc-paramtype.enum.js';
import { createPipesRpcParamDecorator } from '../utils/param.utils.js';

export function Payload(): ParameterDecorator;
export function Payload(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Payload(
  propertyKey?: string,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Payload(
  propertyKey: string,
  options: ParameterDecoratorOptions,
): ParameterDecorator;
export function Payload(options: ParameterDecoratorOptions): ParameterDecorator;
export function Payload(
  propertyOrPipe?:
    | string
    | (Type<PipeTransform> | PipeTransform)
    | ParameterDecoratorOptions,
  optionsOrPipe?:
    | ParameterDecoratorOptions
    | Type<PipeTransform>
    | PipeTransform,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator {
  return createPipesRpcParamDecorator(RpcParamtype.PAYLOAD)(
    propertyOrPipe,
    optionsOrPipe,
    ...pipes,
  );
}
