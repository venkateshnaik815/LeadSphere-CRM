package com.leadsphere.crm.patterns;

import abstractextensions.UnitExtension;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Unit {

  private String name;
  protected UnitExtension unitExtension = null;

  public Unit(String name) {
    this.name = name;
  }

  public UnitExtension getUnitExtension(String extensionName) {
    return null;
  }
}
