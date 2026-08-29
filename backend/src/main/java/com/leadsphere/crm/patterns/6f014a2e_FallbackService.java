package com.leadsphere.crm.patterns;

public class FallbackService implements RemoteService {
  private final String fallbackResponse;

  public FallbackService(String fallbackResponse) {
    this.fallbackResponse = fallbackResponse;
  }

  @Override
  public String execute() {
    return fallbackResponse;
  }
}
