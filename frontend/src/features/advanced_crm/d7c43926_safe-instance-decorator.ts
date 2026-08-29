// @ts-nocheck
import { Logger } from '@nestjs/common';

type InstanceDecorator = (target: unknown) => unknown;

const logger = new Logger('InstrumentLogger');

export function makeSafeInstanceDecorator(
  decorator: InstanceDecorator,
): InstanceDecorator {
  return (target: unknown) => {
    try {
      return decorator(target);
    } catch (err) {
      logger.warn(
        `The "instanceDecorator" function threw an error while decorating an instance (${
          (err as Error)?.message ?? err
        }). The undecorated instance will be used instead.`,
      );
      return target;
    }
  };
}
