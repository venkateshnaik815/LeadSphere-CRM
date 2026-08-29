package com.leadsphere.crm.patterns;

public class IdNotFoundException extends RuntimeException {
  public IdNotFoundException(final String message) {
    super(message);
  }
}
