package com.leadsphere.crm.patterns;

public enum MenuItem {
  HOME("Home"),
  PRODUCTS("Products"),
  COMPANY("Company");

  private final String title;

  MenuItem(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }
}
