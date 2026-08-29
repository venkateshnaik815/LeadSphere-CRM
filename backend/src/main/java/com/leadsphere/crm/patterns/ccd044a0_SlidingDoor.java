package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SlidingDoor implements AutoCloseable {

  public SlidingDoor() {
    LOGGER.info("Sliding door opens.");
  }

  @Override
  public void close() {
    LOGGER.info("Sliding door closes.");
  }
}
