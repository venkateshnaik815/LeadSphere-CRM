// @ts-nocheck
import type { StandardSchemaV1 } from '@standard-schema/spec';
import {
  RESPONSE_PASSTHROUGH_METADATA,
  ROUTE_ARGS_METADATA,
} from '../../constants.js';
import { RouteParamtypes } from '../../enums/route-paramtypes.enum.js';
import { PipeTransform } from '../../index.js';
import { Type } from '../../interfaces/index.js';
import { isNil, isString } from '../../utils/shared.utils.js';
import { isParameterDecoratorOptions } from '../../utils/parameter-decorator-options.util.js';

export interface ParameterDecoratorOptions {
  schema?: StandardSchemaV1;
  pipes?: (Type<PipeTransform> | PipeTransform)[];
}

export interface ResponseDecoratorOptions {
  passthrough: boolean;
}

export type ParamData = object | string | number;
export interface RouteParamMetadata {
  index: number;
  data?: ParamData;
}

export function assignMetadata<TParamtype = any, TArgs = any>(
  args: TArgs,
  paramtype: TParamtype,
  index: number,
  options?: ({ data?: ParamData } & ParameterDecoratorOptions) | ParamData,
  ...legacyPipes: (Type<PipeTransform> | PipeTransform)[]
) {
  // Callers built against the pre-v12 signature
  // `assignMetadata(args, paramtype, index, data?, ...pipes)` pass the raw
  // `data` value (and pipes positionally); detect that shape and normalize it
  // instead of silently misreading `data` as the options object.
  const isOptionsObject =
    options !== null &&
    typeof options === 'object' &&
    (isParameterDecoratorOptions(options) || 'data' in options) &&
    !('transform' in options) &&
    legacyPipes.length === 0;
  const normalizedOptions: { data?: ParamData } & ParameterDecoratorOptions =
    isOptionsObject
      ? (options as { data?: ParamData } & ParameterDecoratorOptions)
      : { data: options as ParamData, pipes: legacyPipes };
  return {
    ...args,
    [`${paramtype as string}:${index}`]: {
      index,
      data: normalizedOptions.data,
      pipes: normalizedOptions.pipes ?? [],
      ...(normalizedOptions.schema !== undefined && {
        schema: normalizedOptions.schema,
      }),
    },
  };
}

function createRouteParamDecorator(paramtype: RouteParamtypes) {
  return (data?: ParamData): ParameterDecorator =>
    (target, key, index) => {
      const args =
        Reflect.getMetadata(ROUTE_ARGS_METADATA, target.constructor, key!) ||
        {};
      Reflect.defineMetadata(
        ROUTE_ARGS_METADATA,
        assignMetadata<RouteParamtypes, Record<number, RouteParamMetadata>>(
          args,
          paramtype,
          index,
          {
            data,
          },
        ),
        target.constructor,
        key!,
      );
    };
}

const createPipesRouteParamDecorator =
  (paramtype: RouteParamtypes) =>
  ({
    data,
    pipes,
    schema,
  }: ParameterDecoratorOptions & { data?: unknown }): ParameterDecorator =>
  (target, key, index) => {
    const args =
      Reflect.getMetadata(ROUTE_ARGS_METADATA, target.constructor, key!) || {};
    const hasParamData = isNil(data) || isString(data);
    const paramData = hasParamData ? data : undefined;
    const paramPipes = hasParamData
      ? (pipes ?? [])
      : [data as Type<PipeTransform> | PipeTransform, ...(pipes ?? [])];

    Reflect.defineMetadata(
      ROUTE_ARGS_METADATA,
      assignMetadata(args, paramtype, index, {
        data: paramData!,
        pipes: paramPipes,
        schema,
      }),
      target.constructor,
      key!,
    );
  };

export const Request: () => ParameterDecorator = createRouteParamDecorator(
  RouteParamtypes.REQUEST,
);

export const Response: (
  options?: ResponseDecoratorOptions,
) => ParameterDecorator =
  (options?: ResponseDecoratorOptions) => (target, key, index) => {
    if (options?.passthrough) {
      Reflect.defineMetadata(
        RESPONSE_PASSTHROUGH_METADATA,
        options?.passthrough,
        target.constructor,
        key!,
      );
    }
    return createRouteParamDecorator(RouteParamtypes.RESPONSE)()(
      target,
      key,
      index,
    );
  };

export const Next: () => ParameterDecorator = createRouteParamDecorator(
  RouteParamtypes.NEXT,
);

export const Ip: () => ParameterDecorator = createRouteParamDecorator(
  RouteParamtypes.IP,
);

export const Session: () => ParameterDecorator = createRouteParamDecorator(
  RouteParamtypes.SESSION,
);

export function UploadedFile(): ParameterDecorator;
export function UploadedFile(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;

export function UploadedFile(
  fileKey?: string,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function UploadedFile(
  fileKey?: string | (Type<PipeTransform> | PipeTransform),
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator {
  return createPipesRouteParamDecorator(RouteParamtypes.FILE)({
    data: fileKey,
    pipes,
  });
}

export function UploadedFiles(): ParameterDecorator;
export function UploadedFiles(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function UploadedFiles(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator {
  return createPipesRouteParamDecorator(RouteParamtypes.FILES)({
    pipes,
  });
}

export const Headers: (property?: string) => ParameterDecorator =
  createRouteParamDecorator(RouteParamtypes.HEADERS);

export function Query(): ParameterDecorator;
export function Query(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Query(
  property: string,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Query(
  property: string,
  options: ParameterDecoratorOptions,
): ParameterDecorator;
export function Query(options: ParameterDecoratorOptions): ParameterDecorator;
export function Query(
  property?:
    | string
    | (Type<PipeTransform> | PipeTransform)
    | ParameterDecoratorOptions,
  optionsOrPipe?:
    | ParameterDecoratorOptions
    | Type<PipeTransform>
    | PipeTransform,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator {
  const isPropertyOptions = isParameterDecoratorOptions(property);

  if (isPropertyOptions) {
    return createPipesRouteParamDecorator(RouteParamtypes.QUERY)({
      pipes: property.pipes,
      schema: property.schema,
    });
  }

  const isOptions = isParameterDecoratorOptions(optionsOrPipe);
  const actualPipes = isOptions
    ? [...(optionsOrPipe.pipes ?? []), ...pipes]
    : ([optionsOrPipe, ...pipes].filter(Boolean) as (
        | Type<PipeTransform>
        | PipeTransform
      )[]);
  return createPipesRouteParamDecorator(RouteParamtypes.QUERY)({
    data: property,
    pipes: actualPipes,
    schema: isOptions ? optionsOrPipe.schema : undefined,
  });
}

export function Body(): ParameterDecorator;
export function Body(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Body(options: ParameterDecoratorOptions): ParameterDecorator;
export function Body(
  property: string,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Body(
  property: string,
  options: ParameterDecoratorOptions,
): ParameterDecorator;
export function Body(
  property?:
    | string
    | (Type<PipeTransform> | PipeTransform)
    | ParameterDecoratorOptions,
  optionsOrPipe?:
    | ParameterDecoratorOptions
    | Type<PipeTransform>
    | PipeTransform,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator {
  const isPropertyOptions = isParameterDecoratorOptions(property);

  if (isPropertyOptions) {
    return createPipesRouteParamDecorator(RouteParamtypes.BODY)({
      pipes: property.pipes,
      schema: property.schema,
    });
  }

  const isOptions = isParameterDecoratorOptions(optionsOrPipe);
  const actualPipes = isOptions
    ? [...(optionsOrPipe.pipes ?? []), ...pipes]
    : ([optionsOrPipe, ...pipes].filter(Boolean) as (
        | Type<PipeTransform>
        | PipeTransform
      )[]);
  return createPipesRouteParamDecorator(RouteParamtypes.BODY)({
    data: property,
    pipes: actualPipes,
    schema: isOptions ? optionsOrPipe.schema : undefined,
  });
}

export function RawBody(): ParameterDecorator;

export function RawBody(
  ...pipes: (
    | Type<PipeTransform<Buffer | undefined>>
    | PipeTransform<Buffer | undefined>
  )[]
): ParameterDecorator;

export function RawBody(options: ParameterDecoratorOptions): ParameterDecorator;

export function RawBody(
  optionsOrPipe?:
    | ParameterDecoratorOptions
    | Type<PipeTransform<Buffer | undefined>>
    | PipeTransform<Buffer | undefined>,
  ...pipes: (
    | Type<PipeTransform<Buffer | undefined>>
    | PipeTransform<Buffer | undefined>
  )[]
): ParameterDecorator {
  const isOptions = isParameterDecoratorOptions(optionsOrPipe);
  const actualPipes = isOptions
    ? [...(optionsOrPipe.pipes ?? []), ...pipes]
    : ([optionsOrPipe, ...pipes].filter(Boolean) as (
        | Type<PipeTransform>
        | PipeTransform
      )[]);
  return createPipesRouteParamDecorator(RouteParamtypes.RAW_BODY)({
    pipes: actualPipes,
    schema: isOptions ? optionsOrPipe.schema : undefined,
  });
}

export function Param(): ParameterDecorator;
export function Param(
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Param(
  property: string,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator;
export function Param(
  property: string,
  options: ParameterDecoratorOptions,
): ParameterDecorator;
export function Param(options: ParameterDecoratorOptions): ParameterDecorator;
export function Param(
  property?:
    | string
    | (Type<PipeTransform> | PipeTransform)
    | ParameterDecoratorOptions,
  optionsOrPipe?:
    | ParameterDecoratorOptions
    | Type<PipeTransform>
    | PipeTransform,
  ...pipes: (Type<PipeTransform> | PipeTransform)[]
): ParameterDecorator {
  const isPropertyOptions = isParameterDecoratorOptions(property);

  if (isPropertyOptions) {
    return createPipesRouteParamDecorator(RouteParamtypes.PARAM)({
      pipes: property.pipes,
      schema: property.schema,
    });
  }

  const isOptions = isParameterDecoratorOptions(optionsOrPipe);
  const actualPipes = isOptions
    ? [...(optionsOrPipe.pipes ?? []), ...pipes]
    : ([optionsOrPipe, ...pipes].filter(Boolean) as (
        | Type<PipeTransform>
        | PipeTransform
      )[]);
  return createPipesRouteParamDecorator(RouteParamtypes.PARAM)({
    data: property,
    pipes: actualPipes,
    schema: isOptions ? optionsOrPipe.schema : undefined,
  });
}

export function HostParam(): ParameterDecorator;
export function HostParam(property: string): ParameterDecorator;
export function HostParam(
  property?: string | (Type<PipeTransform> | PipeTransform),
): ParameterDecorator {
  return createRouteParamDecorator(RouteParamtypes.HOST)(property);
}

export const Req = Request;

export const Res = Response;
