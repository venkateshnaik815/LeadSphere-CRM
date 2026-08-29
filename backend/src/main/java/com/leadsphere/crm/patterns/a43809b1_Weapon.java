package com.leadsphere.crm.patterns;

public enum Weapon {
  DAGGER,
  SWORD,
  AXE,
  WARHAMMER,
  BOW;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
