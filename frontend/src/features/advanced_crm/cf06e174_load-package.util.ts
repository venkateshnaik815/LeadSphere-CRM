// @ts-nocheck
import { createRequire } from 'module';
import { Logger } from '../services/logger.service.js';

const MISSING_REQUIRED_DEPENDENCY = (name: string, reason: string) =>
  `The "${name}" package is missing. Please, make sure to install it to use ${reason}.`;

const logger = new Logger('PackageLoader');

const packageCache = new Map<string, any>();

export async function loadPackage(
  packageName: string,
  context: string,
  loaderFn?: Function,
) {
  const cached = packageCache.get(packageName);
  if (cached) {
    return cached;
  }
  try {
    const pkg = loaderFn ? await loaderFn() : await import(packageName);
    packageCache.set(packageName, pkg);
    return pkg;
  } catch (e) {
    logger.error(MISSING_REQUIRED_DEPENDENCY(packageName, context));
    Logger.flush();
    process.exit(1);
  }
}

export function loadPackageSync(
  packageName: string,
  context: string,
  loaderFn?: () => any,
): any {
  const cached = packageCache.get(packageName);
  if (cached) {
    return cached;
  }
  try {
    const pkg = loaderFn
      ? loaderFn()
      : createRequire(import.meta.url)(packageName);
    packageCache.set(packageName, pkg);
    return pkg;
  } catch (e) {
    logger.error(MISSING_REQUIRED_DEPENDENCY(packageName, context));
    Logger.flush();
    process.exit(1);
  }
}

export function loadPackageCached(packageName: string, context?: string): any {
  const cached = packageCache.get(packageName);
  if (!cached) {
    if (context) {
      // The package was not preloaded (typically because it is not
      // installed). Fall back to a synchronous load so the user gets the
      // actionable "package is missing" message instead of an internal error.
      return loadPackageSync(packageName, context);
    }
    throw new Error(
      `Package "${packageName}" has not been loaded yet. ` +
        `Ensure loadPackage("${packageName}", ...) has been awaited before calling loadPackageCached.`,
    );
  }
  return cached;
}

export async function tryLoadPackage(
  packageName: string,
  loaderFn?: Function,
): Promise<any> {
  const cached = packageCache.get(packageName);
  if (cached) {
    return cached;
  }
  try {
    const pkg = loaderFn ? await loaderFn() : await import(packageName);
    packageCache.set(packageName, pkg);
    return pkg;
  } catch {
    return null;
  }
}
