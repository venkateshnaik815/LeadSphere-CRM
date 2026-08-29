package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoisonPotion implements Potion {

  @Override
  public void drink() {
    LOGGER.info("Urgh! This is poisonous. (Potion={})", System.identityHashCode(this));
  }
}
