package com.leadsphere.crm.patterns;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Weekday {
  MONDAY("Monday"),
  TUESDAY("Tuesday"),
  WEDNESDAY("Wednesday"),
  THURSDAY("Thursday"),
  FRIDAY("Friday"),
  SATURDAY("Saturday"),
  SUNDAY("Sunday");

  private final String description;

  @Override
  public String toString() {
    return description;
  }
}
