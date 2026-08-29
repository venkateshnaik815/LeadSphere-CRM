package com.leadsphere.crm.patterns;

import java.util.List;

public class DisjunctionSelector<T> extends AbstractSelector<T> {

  private final List<AbstractSelector<T>> leafComponents;

  @SafeVarargs
  DisjunctionSelector(AbstractSelector<T>... selectors) {
    this.leafComponents = List.of(selectors);
  }

  @Override
  public boolean test(T t) {
    return leafComponents.stream().anyMatch(comp -> comp.test(t));
  }
}
