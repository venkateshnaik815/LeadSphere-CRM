package com.leadsphere.crm.patterns;

import java.io.Serial;

public class LongRunningEventException extends Exception {

  @Serial private static final long serialVersionUID = -483423544320148809L;

  public LongRunningEventException(String message) {
    super(message);
  }
}
