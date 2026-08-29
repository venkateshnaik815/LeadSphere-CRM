package com.leadsphere.crm.patterns;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Oliphaunt {

  private static final AtomicInteger counter = new AtomicInteger(0);

  @Getter private final int id;

  public Oliphaunt() {
    id = counter.incrementAndGet();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      LOGGER.error("Error occurred: ", e);
    }
  }

  @Override
  public String toString() {
    return String.format("Oliphaunt id=%d", id);
  }
}
