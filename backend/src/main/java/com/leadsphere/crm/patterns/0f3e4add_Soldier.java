package com.leadsphere.crm.patterns;

import abstractextensions.SoldierExtension;
import lombok.extern.slf4j.Slf4j;
import units.SoldierUnit;

@Slf4j
public record Soldier(SoldierUnit unit) implements SoldierExtension {

  @Override
  public void soldierReady() {
    LOGGER.info("[Soldier] " + unit.getName() + " is ready!");
  }
}
