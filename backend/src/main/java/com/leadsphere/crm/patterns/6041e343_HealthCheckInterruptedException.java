package com.leadsphere.crm.patterns;

public class HealthCheckInterruptedException extends RuntimeException {
  public HealthCheckInterruptedException(Throwable cause) {
    super("Health check interrupted", cause);
  }
}
