package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrengthPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("You feel strong. (Potion={})", System.identityHashCode(this));
  }
}
