// @ts-nocheck
import type { VersioningOptions } from '@nestjs/common';
import type { VersionValue } from '@nestjs/common/internal';

export interface RoutePathMetadata {
  ctrlPath?: string;

  methodPath?: string;

  globalPrefix?: string;

  modulePath?: string;

  controllerVersion?: VersionValue;

  methodVersion?: VersionValue;

  versioningOptions?: VersioningOptions;
}
