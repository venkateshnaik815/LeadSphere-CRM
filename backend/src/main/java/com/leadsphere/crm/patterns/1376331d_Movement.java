package com.leadsphere.crm.patterns;

public enum Movement {
  WALKING("walking"),
  SWIMMING("swimming"),
  FLYING("flying");

  private final String title;

  Movement(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }
}
