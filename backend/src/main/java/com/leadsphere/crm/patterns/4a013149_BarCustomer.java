package com.leadsphere.crm.patterns;

import java.security.InvalidParameterException;
import lombok.Getter;

@Getter
public class BarCustomer {

  private final String name;
  private final int allowedCallsPerSecond;

  public BarCustomer(String name, int allowedCallsPerSecond, CallsCount callsCount) {
    if (allowedCallsPerSecond < 0) {
      throw new InvalidParameterException("Number of calls less than 0 not allowed");
    }
    this.name = name;
    this.allowedCallsPerSecond = allowedCallsPerSecond;
    callsCount.addTenant(name);
  }
}
