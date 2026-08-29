package com.leadsphere.crm.patterns;

import java.io.Serial;

public class CustomException extends Exception {

  @Serial private static final long serialVersionUID = 1L;

  public CustomException(String message, Throwable cause) {
    super(message, cause);
  }
}
