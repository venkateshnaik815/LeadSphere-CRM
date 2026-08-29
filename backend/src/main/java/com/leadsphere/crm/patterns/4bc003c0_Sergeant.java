package com.leadsphere.crm.patterns;

import abstractextensions.SergeantExtension;
import lombok.extern.slf4j.Slf4j;
import units.SergeantUnit;

@Slf4j
public record Sergeant(SergeantUnit unit) implements SergeantExtension {

  @Override
  public void sergeantReady() {
    LOGGER.info("[Sergeant] " + unit.getName() + " is ready!");
  }
}
