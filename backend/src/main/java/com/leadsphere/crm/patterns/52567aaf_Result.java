package com.leadsphere.crm.patterns;

public abstract class Result<T> {

  public final T data;

  public Result(T data) {
    this.data = data;
  }
}
