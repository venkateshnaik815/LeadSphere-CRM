package com.leadsphere.crm.patterns;

public class ThrottlingException extends RateLimitException {
  private final String serviceName;
  private final String errorCode;

  public ThrottlingException(String serviceName, String operationName, long retryAfterMillis) {
    super("AWS throttling error for " + serviceName + "/" + operationName, retryAfterMillis);
    this.serviceName = serviceName;
    this.errorCode = "ThrottlingException";
  }

  public String getServiceName() {
    return serviceName;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
