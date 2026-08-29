package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Tobacco {

  public void smoke(Wizard wizard) {
    LOGGER.info(
        "{} smoking {}", wizard.getClass().getSimpleName(), this.getClass().getSimpleName());
  }
}
