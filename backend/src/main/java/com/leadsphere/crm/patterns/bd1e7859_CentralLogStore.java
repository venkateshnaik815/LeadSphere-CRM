package com.leadsphere.crm.patterns;

import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CentralLogStore {

  private final ConcurrentLinkedQueue<LogEntry> logs = new ConcurrentLinkedQueue<>();

  public void storeLog(LogEntry logEntry) {
    if (logEntry == null) {
      LOGGER.error("Received null log entry. Skipping.");
      return;
    }
    logs.offer(logEntry);
  }

  public void displayLogs() {
    LOGGER.info("----- Centralized Logs -----");
    for (LogEntry logEntry : logs) {
      LOGGER.info(
          logEntry.getTimestamp() + " [" + logEntry.getLevel() + "] " + logEntry.getMessage());
    }
  }
}
