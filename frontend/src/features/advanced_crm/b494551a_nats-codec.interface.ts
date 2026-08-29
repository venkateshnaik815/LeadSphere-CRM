// @ts-nocheck
export interface NatsCodec<T> {
  encode(d: T): Uint8Array;
  decode(a: Uint8Array): T;
}
