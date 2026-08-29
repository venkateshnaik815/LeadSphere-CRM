package com.leadsphere.crm.patterns;

public class PrimaryService implements RemoteService {
  private final long latencyMs;
  private final String response;
  private final boolean shouldThrowException;

  public PrimaryService(String response, long latencyMs, boolean shouldThrowException) {
    this.latencyMs = latencyMs;
    this.response = response;
    this.shouldThrowException = shouldThrowException;
  }

  @Override
  public String execute() throws Exception {
    if (shouldThrowException) {
      throw new RuntimeException("Primary service failed!");
    }
    if (latencyMs > 0) {
      Thread.sleep(latencyMs);
    }
    return response;
  }
}
