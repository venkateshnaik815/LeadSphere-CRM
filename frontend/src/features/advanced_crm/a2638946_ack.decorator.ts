// @ts-nocheck
import { WsParamtype } from '../enums/ws-paramtype.enum.js';
import { createPipesWsParamDecorator } from '../utils/param.utils.js';

export function Ack(): ParameterDecorator {
  return createPipesWsParamDecorator(WsParamtype.ACK)();
}
