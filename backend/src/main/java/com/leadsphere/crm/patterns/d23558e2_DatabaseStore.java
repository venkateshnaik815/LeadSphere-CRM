
package com.leadsphere.crm.patterns;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseStore {

  @Getter private final WriteAheadLog wal;
  private final Map<String, String> memTable = new HashMap<>();

  public DatabaseStore(WriteAheadLog wal) {
    this.wal = wal;
  }

  public synchronized void put(String key, String value) throws IOException {
    wal.append(OperationType.SET, key, value);
    memTable.put(key, value);
    LOGGER.info("Applied SET operation to MemTable: {} = {}", key, value);
  }

  public synchronized void delete(String key) throws IOException {
    wal.append(OperationType.DELETE, key, null);
    memTable.remove(key);
    LOGGER.info("Applied DELETE operation to MemTable: {}", key);
  }

  public synchronized String get(String key) {
    return memTable.get(key);
  }

  public synchronized Map<String, String> getMemTableSnapshot() {
    return Collections.unmodifiableMap(new HashMap<>(memTable));
  }

  public synchronized void checkpoint() throws IOException {
    wal.append(OperationType.CHECKPOINT, null, null);
    LOGGER.info("Checkpoint written to WAL.");
  }

  public synchronized void simulateCrash() {
    memTable.clear();
    LOGGER.info("!!! SIMULATED SYSTEM CRASH: In-memory MemTable has been wiped !!!");
  }

  public synchronized void recover() {
    LOGGER.info("Starting recovery process from WAL...");
    memTable.clear();
    List<LogEntry> entries = wal.readAll();

    for (LogEntry entry : entries) {
      if (entry.getType() == OperationType.SET) {
        memTable.put(entry.getKey(), entry.getValue());
      } else if (entry.getType() == OperationType.DELETE) {
        memTable.remove(entry.getKey());
      }
    }
    LOGGER.info("Recovery completed. Replayed {} log entries into MemTable.", entries.size());
  }
}
