// @ts-nocheck
export const DEFAULT_METHOD_KEY = 'register';
export const DEFAULT_FACTORY_CLASS_METHOD_KEY = 'create';

export const ASYNC_METHOD_SUFFIX = 'Async';
export const CONFIGURABLE_MODULE_ID = 'CONFIGURABLE_MODULE_ID';

export const ASYNC_OPTIONS_METADATA_KEYS = [
  'useFactory',
  'useClass',
  'useExisting',
  'inject',
  'imports',
  'provideInjectionTokensFrom',
] as const;
