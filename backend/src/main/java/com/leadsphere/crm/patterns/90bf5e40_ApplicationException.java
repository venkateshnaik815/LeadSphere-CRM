package com.leadsphere.crm.patterns;

import java.io.Serial;

public class ApplicationException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public ApplicationException(Throwable cause) {
    super(cause);
  }
}
