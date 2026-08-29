// @ts-nocheck
export class Barrier {
  private currentCount: number;
  private targetCount: number;
  private promise: Promise<void>;
  private resolve: () => void;

  constructor(targetCount: number) {
    this.currentCount = 0;
    this.targetCount = targetCount;

    this.promise = new Promise<void>(resolve => {
      this.resolve = resolve;
    });

    if (targetCount === 0) {
      this.resolve();
    }
  }

  public signal(): void {
    this.currentCount += 1;
    if (this.currentCount >= this.targetCount) {
      this.resolve();
    }
  }

  public async wait(): Promise<void> {
    return this.promise;
  }

  public async signalAndWait(): Promise<void> {
    this.signal();
    return this.wait();
  }
}
