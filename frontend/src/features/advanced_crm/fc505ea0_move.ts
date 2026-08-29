// @ts-nocheck
import { dest, src, task } from 'gulp';
import { join } from 'node:path';
import { samplePath } from '../config.js';
import { containsPackageJson, getDirs } from '../util/task-helpers.js';

const distFiles = src([
  'packages/**/*.js',
  'packages/**/*.d.ts',
  'packages/**/package.json',
]);

function moveToNodeModules() {
  return distFiles.pipe(dest('node_modules/@nestjs'));
}

function moveToSamples() {
  const samplesDirs = getDirs(samplePath);

  const flattenedSampleDirs: string[] = [];

  for (const sampleDir of samplesDirs) {
    if (containsPackageJson(sampleDir)) {
      flattenedSampleDirs.push(sampleDir);
    } else {
      flattenedSampleDirs.push(...getDirs(sampleDir));
    }
  }

  return flattenedSampleDirs.reduce(
    (distFile, dir) => distFile.pipe(dest(join(dir, '/node_modules/@nestjs'))),
    distFiles,
  );
}

task('move:node_modules', moveToNodeModules);
task('move:samples', moveToSamples);
