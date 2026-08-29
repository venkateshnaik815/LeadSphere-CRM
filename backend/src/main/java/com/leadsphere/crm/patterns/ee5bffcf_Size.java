package com.leadsphere.crm.patterns;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Size {
  SMALL("small"),
  NORMAL("normal");

  private final String title;

  @Override
  public String toString() {
    return title;
  }
}
