package com.leadsphere.crm.patterns;

import java.io.Serial;

public class EventDoesNotExistException extends Exception {

  @Serial private static final long serialVersionUID = -3398463738273811509L;

  public EventDoesNotExistException(String message) {
    super(message);
  }
}
