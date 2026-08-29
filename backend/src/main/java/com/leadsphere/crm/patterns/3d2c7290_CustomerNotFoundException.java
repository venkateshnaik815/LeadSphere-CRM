package com.leadsphere.crm.patterns;

import java.io.Serial;

public final class CustomerNotFoundException extends BusinessException {

  @Serial private static final long serialVersionUID = -6972888602621778664L;

  public CustomerNotFoundException(String message) {
    super(message);
  }
}
