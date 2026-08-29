package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExternalServiceB implements Gateway {
  @Override
  public void execute() throws Exception {
    LOGGER.info("Executing Service B");
    // Simulate a time-consuming task
    Thread.sleep(1000);
  }
}
