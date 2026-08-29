
package com.leadsphere.crm.patterns;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    try {
      File logFile = File.createTempFile("wal_demo", ".log");
      logFile.deleteOnExit();

      LOGGER.info(
          "=== 1. Initializing Storage Engine with WAL at {} ===", logFile.getAbsolutePath());
      WriteAheadLog wal = new WriteAheadLog(logFile);
      DatabaseStore store = new DatabaseStore(wal);

      LOGGER.info("=== 2. Performing Data Operations (Write-Ahead Logging) ===");
      store.put("user:101", "Alice");
      store.put("user:102", "Bob");
      store.put("user:103", "Charlie");
      store.put("user:102", "Bob Smith");
      store.delete("user:103");
      store.checkpoint();

      LOGGER.info("MemTable snapshot before crash: {}", store.getMemTableSnapshot());

      LOGGER.info("=== 3. Simulating Unexpected System Crash ===");
      store.simulateCrash();
      LOGGER.info("MemTable snapshot after crash: {}", store.getMemTableSnapshot());

      LOGGER.info("=== 4. System Restart & Recovery from WAL ===");
      store.recover();
      LOGGER.info("MemTable snapshot post recovery: {}", store.getMemTableSnapshot());

      if (logFile.exists()) {
        logFile.delete();
      }
    } catch (IOException e) {
      LOGGER.error("An error occurred during WAL demonstration: {}", e.getMessage(), e);
    }
  }
}
