// @ts-nocheck
import { RequestMethod } from '@nestjs/common';
import { type VersionValue } from '@nestjs/common/internal';
import { InstanceWrapper } from '../../injector/instance-wrapper.js';
import { RouterProxyCallback } from '../router-proxy.js';

export type ResolvedRouteHandler = (...args: unknown[]) => unknown;

export interface ResolvedRoute {
  method: RequestMethod;
  path: string;
  rawPath?: string;
  host: string | RegExp | Array<string | RegExp> | undefined;
  version: VersionValue | undefined;
  methodVersion: VersionValue | undefined;
  controllerVersion: VersionValue | undefined;
  handler: ResolvedRouteHandler;
  targetCallback: RouterProxyCallback;
  methodName: string;
  instanceWrapper: InstanceWrapper;
}
