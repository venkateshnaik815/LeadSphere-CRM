package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Fatigue {
  ALERT("alert"),
  TIRED("tired"),
  SLEEPING("sleeping");

  private final String title;

  @Override
  public String toString() {
    return title;
  }
}
