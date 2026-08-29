package com.leadsphere.crm.patterns;

public record OrcWeapon(WeaponType weaponType) implements Weapon {

  @Override
  public String toString() {
    return "an orcish " + weaponType;
  }
}
