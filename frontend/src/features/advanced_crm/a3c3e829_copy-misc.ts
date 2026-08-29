// @ts-nocheck
import { dest, src, task } from 'gulp';
import { packagePaths } from '../config.js';

function copyMisc(): NodeJS.ReadWriteStream {
  const miscFiles = src(['Readme.md', 'LICENSE', '.npmignore']);
  // Since `dest()` does not take a string-array, we have to append it
  // ourselves
  return packagePaths.reduce(
    (stream, packagePath) => stream.pipe(dest(packagePath)),
    miscFiles,
  );
}

task('copy-misc', copyMisc);
