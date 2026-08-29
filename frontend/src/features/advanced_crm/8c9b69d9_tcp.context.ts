// @ts-nocheck
import { TcpSocket } from '../helpers/index.js';
import { BaseRpcContext } from './base-rpc.context.js';

type TcpContextArgs = [TcpSocket, string];

export class TcpContext extends BaseRpcContext<TcpContextArgs> {
  constructor(args: TcpContextArgs) {
    super(args);
  }

  getSocketRef() {
    return this.args[0];
  }

  getPattern() {
    return this.args[1];
  }
}
