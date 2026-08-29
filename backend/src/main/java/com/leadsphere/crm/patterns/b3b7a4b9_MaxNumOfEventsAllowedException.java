package com.leadsphere.crm.patterns;

import java.io.Serial;

public class MaxNumOfEventsAllowedException extends Exception {

  @Serial private static final long serialVersionUID = -8430876973516292695L;

  public MaxNumOfEventsAllowedException(String message) {
    super(message);
  }
}
