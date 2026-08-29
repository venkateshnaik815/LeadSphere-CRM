package com.leadsphere.crm.patterns;

public interface RateLimiter {
  void check(String serviceName, String operationName) throws RateLimitException;
}
