package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Heavy {

  public Heavy() {
    LOGGER.info("Creating Heavy ...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      LOGGER.error("Exception caught.", e);
    }
    LOGGER.info("... Heavy created");
  }
}
