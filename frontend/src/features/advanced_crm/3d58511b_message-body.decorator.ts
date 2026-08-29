// @ts-nocheck
import type {
  ParameterDecoratorOptions,
  PipeTransform,
  Type,
} from '@nestjs/common';
import { WsParamtype } from '../enums/ws-paramtype.enum.js';
import { createPipesWsParamDecorator } from '../utils/param.utils.js';

export function MessageBody(): ParameterDecorator;
export function MessageBody(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function MessageBody(
  propertyKey: string,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function MessageBody(
  propertyKey: string,
  options: ParameterDecoratorOptions,
): ParameterDecorator;
export function MessageBody(
  options: ParameterDecoratorOptions,
): ParameterDecorator;
export function MessageBody(
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
  return createPipesWsParamDecorator(WsParamtype.PAYLOAD)(
    propertyOrPipe,
    optionsOrPipe,
    ...pipes,
  );
}
