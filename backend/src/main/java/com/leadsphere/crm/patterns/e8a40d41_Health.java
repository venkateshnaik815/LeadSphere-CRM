package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Health {
  HEALTHY("healthy"),
  WOUNDED("wounded"),
  DEAD("dead");

  private final String title;

  @Override
  public String toString() {
    return title;
  }
}
