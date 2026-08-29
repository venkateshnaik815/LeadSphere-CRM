package com.leadsphere.crm.patterns;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Prototype<T> implements Cloneable {

  @SuppressWarnings("unchecked")
  @SneakyThrows
  public T copy() {
    return (T) super.clone();
  }
}
