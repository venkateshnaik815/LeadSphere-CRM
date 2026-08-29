package com.leadsphere.crm.patterns;

public enum Size {
  SMALL("small"),
  NORMAL("normal"),
  LARGE("large");

  private final String title;

  Size(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }
}
