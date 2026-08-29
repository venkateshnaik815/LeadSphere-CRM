package com.leadsphere.crm.patterns;

import java.io.Serial;

public class LockingException extends RuntimeException {

  @Serial private static final long serialVersionUID = 8556381044865867037L;

  public LockingException(String message) {
    super(message);
  }
}
