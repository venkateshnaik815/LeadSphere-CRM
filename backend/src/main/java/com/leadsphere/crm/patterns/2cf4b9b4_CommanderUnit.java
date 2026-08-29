package com.leadsphere.crm.patterns;

import abstractextensions.UnitExtension;
import concreteextensions.Commander;
import java.util.Optional;

public class CommanderUnit extends Unit {

  public CommanderUnit(String name) {
    super(name);
  }

  @Override
  public UnitExtension getUnitExtension(String extensionName) {

    if (extensionName.equals("CommanderExtension")) {
      return Optional.ofNullable(unitExtension).orElseGet(() -> new Commander(this));
    }

    return super.getUnitExtension(extensionName);
  }
}
