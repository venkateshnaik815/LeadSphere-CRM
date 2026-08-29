
package com.leadsphere.crm.patterns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteAheadLog {

  @Getter private final File logFile;
  private final AtomicLong sequenceNumberCounter = new AtomicLong(0);

  public WriteAheadLog(File logFile) {
    this.logFile = logFile;
    initSequenceNumber();
  }

  private void initSequenceNumber() {
    if (logFile.exists()) {
      List<LogEntry> existingEntries = readAll();
      if (!existingEntries.isEmpty()) {
        long maxSeq = existingEntries.get(existingEntries.size() - 1).getSequenceNumber();
        sequenceNumberCounter.set(maxSeq);
      }
    }
  }

  public synchronized LogEntry append(OperationType type, String key, String value)
      throws IOException {
    long nextSeq = sequenceNumberCounter.incrementAndGet();
    LogEntry entry = new LogEntry(nextSeq, type, key, value);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
      writer.write(entry.toLogString());
      writer.newLine();
      writer.flush();
    }
    LOGGER.info("WAL Entry appended & flushed to disk: {}", entry);
    return entry;
  }

  public synchronized List<LogEntry> readAll() {
    List<LogEntry> entries = new ArrayList<>();
    if (!logFile.exists()) {
      return entries;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.isBlank()) {
          entries.add(LogEntry.fromLogString(line));
        }
      }
    } catch (IOException e) {
      LOGGER.error("Failed to read log entries from WAL file: {}", logFile.getAbsolutePath(), e);
    }
    return entries;
  }

  public synchronized void clear() {
    if (logFile.exists()) {
      try {
        Files.delete(logFile.toPath());
        sequenceNumberCounter.set(0);
        LOGGER.info("WAL log cleared successfully.");
      } catch (IOException e) {
        LOGGER.error("Failed to clear WAL file: {}", logFile.getAbsolutePath(), e);
      }
    }
  }
}
