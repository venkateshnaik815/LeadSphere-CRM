package com.leadsphere.crm.patterns;

import java.util.List;

public abstract class Input<T> {

  public final T data;

  public Input(T data) {
    this.data = data;
  }

  public abstract List<Input<T>> divideData(int num);
}
