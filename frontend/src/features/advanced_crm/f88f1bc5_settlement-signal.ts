// @ts-nocheck
export class SettlementSignal {
  private readonly _refs = new Set();
  private readonly settledPromise: Promise<unknown>;
  private settleFn!: (err?: unknown) => void;
  private completed = false;

  constructor() {
    this.settledPromise = new Promise<unknown>(resolve => {
      this.settleFn = resolve;
    });
  }

  public complete() {
    this.completed = true;
    this.settleFn();
  }

  public error(err: unknown) {
    this.completed = true;
    this.settleFn(err);
  }

  public asPromise() {
    return this.settledPromise;
  }

  public insertRef(wrapperId: string) {
    this._refs.add(wrapperId);
  }

  public isCycle(wrapperId: string) {
    return !this.completed && this._refs.has(wrapperId);
  }
}
