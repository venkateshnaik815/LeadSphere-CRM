// @ts-nocheck
import autocannon from 'autocannon';

export type RunOptions = autocannon.Options & {
  verbose?: boolean;
};

export const run = (options: RunOptions) =>
  new Promise<autocannon.Result>((resolve, reject) => {
    const { verbose = false, ...autocannonOptions } = options;

    const instance = autocannon(autocannonOptions, (err, result) => {
      if (err) {
        reject(err);
      } else {
        resolve(result);
      }
    });

    if (verbose) {
      autocannon.track(instance);
    }
  });
