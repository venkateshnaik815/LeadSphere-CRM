// @ts-nocheck
import { Module } from '@nestjs/common';
import { MetadataScanner } from '../metadata-scanner.js';
import { DiscoveryService } from './discovery-service.js';

@Module({
  providers: [MetadataScanner, DiscoveryService],
  exports: [MetadataScanner, DiscoveryService],
})
export class DiscoveryModule {}
