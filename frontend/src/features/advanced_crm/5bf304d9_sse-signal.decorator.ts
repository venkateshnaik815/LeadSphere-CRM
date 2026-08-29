// @ts-nocheck
import { ExecutionContext } from '../../interfaces/index.js';
import { createParamDecorator } from './create-route-param-metadata.decorator.js';

export const SSE_ABORT_CONTROLLER = Symbol('SSE_ABORT_CONTROLLER');

export const SseSignal: () => ParameterDecorator = createParamDecorator(
  (_data: unknown, ctx: ExecutionContext): AbortSignal | undefined => {
    const request = ctx.switchToHttp().getRequest();
    const controller: AbortController | undefined =
      request?.[SSE_ABORT_CONTROLLER];
    return controller?.signal;
  },
);
