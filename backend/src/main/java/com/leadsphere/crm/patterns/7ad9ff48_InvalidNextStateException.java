package com.leadsphere.crm.patterns;

public class InvalidNextStateException extends RuntimeException {
  public InvalidNextStateException(String s) {
    super(s);
  }
}
