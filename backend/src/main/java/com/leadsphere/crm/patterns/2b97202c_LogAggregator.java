package com.leadsphere.crm.patterns;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogAggregator {

  private static final int BUFFER_THRESHOLD = 3;
  private final CentralLogStore centralLogStore;
  private final ConcurrentLinkedQueue<LogEntry> buffer = new ConcurrentLinkedQueue<>();
  private final LogLevel minLogLevel;
  private final ExecutorService executorService = Executors.newSingleThreadExecutor();
  private final AtomicInteger logCount = new AtomicInteger(0);

  public LogAggregator(CentralLogStore centralLogStore, LogLevel minLogLevel) {
    this.centralLogStore = centralLogStore;
    this.minLogLevel = minLogLevel;
    startBufferFlusher();
  }

  public void collectLog(LogEntry logEntry) {
    if (logEntry.getLevel() == null || minLogLevel == null) {
      LOGGER.warn("Log level or threshold level is null. Skipping.");
      return;
    }

    if (logEntry.getLevel().compareTo(minLogLevel) < 0) {
      LOGGER.debug("Log level below threshold. Skipping.");
      return;
    }

    buffer.offer(logEntry);

    if (logCount.incrementAndGet() >= BUFFER_THRESHOLD) {
      flushBuffer();
    }
  }

  public void stop() throws InterruptedException {
    executorService.shutdownNow();
    if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
      LOGGER.error("Log aggregator did not terminate.");
    }
    flushBuffer();
  }

  private void flushBuffer() {
    LogEntry logEntry;
    while ((logEntry = buffer.poll()) != null) {
      centralLogStore.storeLog(logEntry);
      logCount.decrementAndGet();
    }
  }

  private void startBufferFlusher() {
    executorService.execute(
        () -> {
          while (!Thread.currentThread().isInterrupted()) {
            try {
              Thread.sleep(5000); // Flush every 5 seconds.
              flushBuffer();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          }
        });
  }
}
