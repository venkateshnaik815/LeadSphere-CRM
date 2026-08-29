package com.leadsphere.crm.patterns;

import java.io.Serial;

public class InvalidOperationException extends Exception {

  @Serial private static final long serialVersionUID = -6191545255213410803L;

  public InvalidOperationException(String message) {
    super(message);
  }
}
