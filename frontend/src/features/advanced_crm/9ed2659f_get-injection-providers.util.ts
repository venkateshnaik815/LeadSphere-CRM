// @ts-nocheck
import { isUndefined } from '../../utils/shared.utils.js';
import {
  FactoryProvider,
  InjectionToken,
  OptionalFactoryDependency,
  Provider,
} from '../../interfaces/index.js';

function isOptionalFactoryDependency(
  value: InjectionToken | OptionalFactoryDependency,
): value is OptionalFactoryDependency {
  return (
    !isUndefined((value as OptionalFactoryDependency).token) &&
    !isUndefined((value as OptionalFactoryDependency).optional) &&
    !(value as any).prototype
  );
}

const mapInjectToTokens = (t: InjectionToken | OptionalFactoryDependency) =>
  isOptionalFactoryDependency(t) ? t.token : t;

export function getInjectionProviders(
  providers: Provider[],
  tokens: FactoryProvider['inject'],
): Provider[] {
  const result: Provider[] = [];
  let search: InjectionToken[] = tokens!.map(mapInjectToTokens);
  while (search.length > 0) {
    const match = (providers ?? []).filter(
      p =>
        !result.includes(p) && // this prevents circular loops and duplication
        (search.includes(p as any) || search.includes((p as any)?.provide)),
    );
    result.push(...match);
    // get injection tokens of the matched providers, if any
    search = match
      .filter(p => (p as any)?.inject)
      .flatMap(p => (p as FactoryProvider).inject!)
      .map(mapInjectToTokens);
  }
  return result;
}
