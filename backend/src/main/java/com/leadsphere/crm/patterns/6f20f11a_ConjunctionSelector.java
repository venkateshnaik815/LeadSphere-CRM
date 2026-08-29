package com.leadsphere.crm.patterns;

import java.util.List;

public class ConjunctionSelector<T> extends AbstractSelector<T> {

  private final List<AbstractSelector<T>> leafComponents;

  @SafeVarargs
  ConjunctionSelector(AbstractSelector<T>... selectors) {
    this.leafComponents = List.of(selectors);
  }

  @Override
  public boolean test(T t) {
    return leafComponents.stream().allMatch(comp -> (comp.test(t)));
  }
}
