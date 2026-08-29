package com.leadsphere.crm.patterns;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class LogProducer {

  private String serviceName;
  private LogAggregator aggregator;

  public void generateLog(LogLevel level, String message) {
    final LogEntry logEntry = new LogEntry(serviceName, level, message, LocalDateTime.now());
    LOGGER.info("Producing log: " + logEntry.getMessage());
    aggregator.collectLog(logEntry);
  }
}
