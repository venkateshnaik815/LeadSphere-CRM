package com.leadsphere.crm.patterns;

public class ServiceUnavailableException extends RateLimitException {
  private final String serviceName;

  public ServiceUnavailableException(String serviceName, long retryAfterMillis) {
    super("Service temporarily unavailable: " + serviceName, retryAfterMillis);
    this.serviceName = serviceName;
  }

  public String getServiceName() {
    return serviceName;
  }
}
