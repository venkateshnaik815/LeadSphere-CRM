package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class App {

  private App() {}

  public static void main(final String[] args) {
    var task = new SimpleTask();

    LOGGER.info("=== Synchronous callback ===");
    task.executeWith(() -> LOGGER.info("Sync callback executed."));

    LOGGER.info("=== Asynchronous callback ===");
    task.executeAsyncWith(() -> LOGGER.info("Async callback executed.")).join();
  }
}
