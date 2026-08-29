package com.leadsphere.crm.patterns;

public interface RateLimitOperation<T> {
  String getServiceName();

  String getOperationName();

  T execute() throws RateLimitException;
}
