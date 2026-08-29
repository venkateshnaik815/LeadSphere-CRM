// @ts-nocheck
import deleteEmpty from 'delete-empty';
import { series, src, task } from 'gulp';
import clean from 'gulp-clean';
import { source } from '../config.js';

function cleanOutput() {
  return src(
    [
      `${source}/**/*.js`,
      `${source}/**/*.d.ts`,
      `${source}/**/*.js.map`,
      `${source}/**/*.d.ts.map`,
    ],
    {
      read: false,
    },
  ).pipe(clean());
}

function cleanDirs(done: () => void) {
  deleteEmpty.sync(`${source}/`);
  done();
}

task('clean:output', cleanOutput);
task('clean:dirs', cleanDirs);
task('clean:bundle', series('clean:output', 'clean:dirs'));
