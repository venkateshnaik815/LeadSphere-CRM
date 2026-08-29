package com.leadsphere.crm.patterns;

public class SubtractionCalculatorAction implements CalculatorAction {
  public static final String SUBTRACTION = "SUBTRACTION";

  @Override
  public String tag() {
    return SUBTRACTION;
  }
}
