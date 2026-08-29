// @ts-nocheck
import { WsParamtype } from '../enums/ws-paramtype.enum.js';
import { createWsParamDecorator } from '../utils/param.utils.js';

export const ConnectedSocket: () => ParameterDecorator = createWsParamDecorator(
  WsParamtype.SOCKET,
);
