package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Nourishment {
  SATURATED("saturated"),
  HUNGRY("hungry"),
  STARVING("starving");

  private final String title;

  @Override
  public String toString() {
    return title;
  }
}
