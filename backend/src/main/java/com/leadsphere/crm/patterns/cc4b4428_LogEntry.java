
package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LogEntry {

  private static final String DELIMITER = "|";

  private final long sequenceNumber;
  private final OperationType type;
  private final String key;
  private final String value;

  public String toLogString() {
    return sequenceNumber
        + DELIMITER
        + type
        + DELIMITER
        + (key != null ? key : "")
        + DELIMITER
        + (value != null ? value : "");
  }

  public static LogEntry fromLogString(String line) {
    if (line == null || line.isBlank()) {
      throw new IllegalArgumentException("Log line cannot be null or blank");
    }
    String[] parts = line.split("\\|", -1);
    if (parts.length < 4) {
      throw new IllegalArgumentException("Invalid log line format: " + line);
    }
    long sequenceNumber = Long.parseLong(parts[0]);
    OperationType type = OperationType.valueOf(parts[1]);
    String key = parts[2].isEmpty() ? null : parts[2];
    String value = parts[3].isEmpty() ? null : parts[3];
    return new LogEntry(sequenceNumber, type, key, value);
  }
}
