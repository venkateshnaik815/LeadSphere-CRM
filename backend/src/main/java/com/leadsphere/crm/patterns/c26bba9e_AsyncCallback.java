package com.leadsphere.crm.patterns;

public interface AsyncCallback<T> {

  void onComplete(T value);

  void onError(Exception ex);
}
