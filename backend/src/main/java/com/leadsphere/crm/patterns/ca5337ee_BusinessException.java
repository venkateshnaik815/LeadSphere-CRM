package com.leadsphere.crm.patterns;

import java.io.Serial;

public class BusinessException extends Exception {
  @Serial private static final long serialVersionUID = 6235833142062144336L;

  public BusinessException(String message) {
    super(message);
  }
}
