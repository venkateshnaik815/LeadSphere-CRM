// @ts-nocheck
export class BaseRpcContext<T = unknown[]> {
  constructor(protected readonly args: T) {}

  getArgs(): T {
    return this.args;
  }

  getArgByIndex(index: number) {
    return this.args[index];
  }
}
