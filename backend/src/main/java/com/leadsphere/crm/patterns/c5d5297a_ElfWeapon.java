package com.leadsphere.crm.patterns;

public record ElfWeapon(WeaponType weaponType) implements Weapon {

  @Override
  public String toString() {
    return "an elven " + weaponType;
  }
}
