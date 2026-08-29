package com.leadsphere.crm.patterns;

import java.util.Objects;
import lombok.Getter;

@Getter
public class Request {

  private final RequestType requestType;

  private final String requestDescription;

  private boolean handled;

  public Request(final RequestType requestType, final String requestDescription) {
    this.requestType = Objects.requireNonNull(requestType);
    this.requestDescription = Objects.requireNonNull(requestDescription);
  }

  public void markHandled() {
    this.handled = true;
  }

  @Override
  public String toString() {
    return getRequestDescription();
  }
}
