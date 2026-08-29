// @ts-nocheck
import { readdirSync, statSync } from 'node:fs';
import { join } from 'node:path';

function isDirectory(path: string) {
  return statSync(path).isDirectory();
}

export function getFolders(dir: string) {
  return readdirSync(dir).filter(file => isDirectory(join(dir, file)));
}

export function getDirs(base: string) {
  return getFolders(base).map(path => `${base}/${path}`);
}

export function containsPackageJson(dir: string) {
  return readdirSync(dir).some(file => file === 'package.json');
}
