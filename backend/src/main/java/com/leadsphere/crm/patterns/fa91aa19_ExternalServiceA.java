package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExternalServiceA implements Gateway {
  @Override
  public void execute() throws Exception {
    LOGGER.info("Executing Service A");
    // Simulate a time-consuming task
    Thread.sleep(1000);
  }
}
