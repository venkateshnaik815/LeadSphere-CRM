package com.leadsphere.crm.patterns;

import abstractextensions.UnitExtension;
import concreteextensions.Sergeant;
import java.util.Optional;

public class SergeantUnit extends Unit {

  public SergeantUnit(String name) {
    super(name);
  }

  @Override
  public UnitExtension getUnitExtension(String extensionName) {

    if (extensionName.equals("SergeantExtension")) {
      return Optional.ofNullable(unitExtension).orElseGet(() -> new Sergeant(this));
    }

    return super.getUnitExtension(extensionName);
  }
}
