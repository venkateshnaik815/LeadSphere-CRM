// @ts-nocheck
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { AbstractHttpAdapter } from '../adapters/http-adapter.js';

export class HttpAdapterHost<
  T extends AbstractHttpAdapter = AbstractHttpAdapter,
> {
  private _httpAdapter?: T;
  private _listen$ = new Subject<void>();
  private _init$ = new ReplaySubject<void>();
  private isListening = false;

  set httpAdapter(httpAdapter: T) {
    this._httpAdapter = httpAdapter;

    this._init$.next();
    this._init$.complete();
  }

  get httpAdapter(): T {
    return this._httpAdapter as T;
  }

  get listen$(): Observable<void> {
    return this._listen$.asObservable();
  }

  get init$(): Observable<void> {
    return this._init$.asObservable();
  }

  set listening(listening: boolean) {
    this.isListening = listening;

    if (listening) {
      this._listen$.next();
      this._listen$.complete();
    }
  }

  get listening(): boolean {
    return this.isListening;
  }
}
