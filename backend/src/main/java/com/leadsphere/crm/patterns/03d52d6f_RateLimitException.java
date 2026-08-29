package com.leadsphere.crm.patterns;

public class RateLimitException extends Exception {
  private final long retryAfterMillis;

  public RateLimitException(String message, long retryAfterMillis) {
    super(message);
    this.retryAfterMillis = retryAfterMillis;
  }

  public long getRetryAfterMillis() {
    return retryAfterMillis;
  }
}
