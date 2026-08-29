package com.leadsphere.crm.patterns;

public interface StateListener<T> {

  void onStateChange(T state);
}
