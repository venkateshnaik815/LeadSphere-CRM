package com.leadsphere.crm.patterns;

public class NegationSelector<T> extends AbstractSelector<T> {

  private final AbstractSelector<T> component;

  NegationSelector(AbstractSelector<T> selector) {
    this.component = selector;
  }

  @Override
  public boolean test(T t) {
    return !(component.test(t));
  }
}
