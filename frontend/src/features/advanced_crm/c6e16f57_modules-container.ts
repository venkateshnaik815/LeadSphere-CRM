// @ts-nocheck
import { Observable, ReplaySubject } from 'rxjs';
import { uid } from 'uid';
import { Module } from './module.js';

export class ModulesContainer extends Map<string, Module> {
  private readonly _applicationId = uid(21);
  private readonly _rpcTargetRegistry$ = new ReplaySubject<any>();

  get applicationId(): string {
    return this._applicationId;
  }

  public getById(id: string): Module | undefined {
    return Array.from(this.values()).find(moduleRef => moduleRef.id === id);
  }

  public getRpcTargetRegistry<T>(): Observable<T> {
    return this._rpcTargetRegistry$.asObservable();
  }

  public addRpcTarget<T>(target: T): void {
    this._rpcTargetRegistry$.next(target);
  }
}
