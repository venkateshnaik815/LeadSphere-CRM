package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExternalServiceC implements Gateway {
  @Override
  public void execute() throws Exception {
    LOGGER.info("Executing Service C");
    // Simulate a time-consuming task
    Thread.sleep(1000);
  }

  public void error() {
    // Simulate an exception
    throw new RuntimeException("Service C encountered an error");
  }
}
