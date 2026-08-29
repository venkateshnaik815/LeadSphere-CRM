package com.leadsphere.crm.patterns;

import java.io.Serial;

public final class DatabaseNotAvailableException extends BusinessException {
  @Serial private static final long serialVersionUID = -3750769625095997799L;

  public DatabaseNotAvailableException(String message) {
    super(message);
  }
}
