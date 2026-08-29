package com.leadsphere.crm.patterns;

import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuardedQueue {
  private final Queue<Integer> sourceList;

  public GuardedQueue() {
    this.sourceList = new LinkedList<>();
  }

  public synchronized Integer get() {
    while (sourceList.isEmpty()) {
      try {
        LOGGER.info("waiting");
        wait();
      } catch (InterruptedException e) {
        LOGGER.error("Error occurred: ", e);
      }
    }
    LOGGER.info("getting");
    return sourceList.peek();
  }

  public synchronized void put(Integer e) {
    LOGGER.info("putting");
    sourceList.add(e);
    LOGGER.info("notifying");
    notify();
  }
}
