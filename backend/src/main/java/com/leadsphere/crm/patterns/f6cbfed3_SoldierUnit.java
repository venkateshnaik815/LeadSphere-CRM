package com.leadsphere.crm.patterns;

import abstractextensions.UnitExtension;
import concreteextensions.Soldier;
import java.util.Optional;

public class SoldierUnit extends Unit {

  public SoldierUnit(String name) {
    super(name);
  }

  @Override
  public UnitExtension getUnitExtension(String extensionName) {

    if (extensionName.equals("SoldierExtension")) {
      return Optional.ofNullable(unitExtension).orElseGet(() -> new Soldier(this));
    }

    return super.getUnitExtension(extensionName);
  }
}
