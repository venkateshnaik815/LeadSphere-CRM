package com.leadsphere.crm.patterns;

public enum Color {
  DARK("dark"),
  LIGHT("light"),
  GREEN("green"),
  RED("red");

  private final String title;

  Color(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }
}
