package com.leadsphere.crm.patterns;

import abstractextensions.CommanderExtension;
import lombok.extern.slf4j.Slf4j;
import units.CommanderUnit;

@Slf4j
public record Commander(CommanderUnit unit) implements CommanderExtension {

  @Override
  public void commanderReady() {
    LOGGER.info("[Commander] " + unit.getName() + " is ready!");
  }
}
