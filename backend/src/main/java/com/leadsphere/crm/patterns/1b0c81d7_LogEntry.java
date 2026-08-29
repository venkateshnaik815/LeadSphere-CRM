package com.leadsphere.crm.patterns;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogEntry {
  private String serviceName;
  private LogLevel level;
  private String message;
  private LocalDateTime timestamp;
}
