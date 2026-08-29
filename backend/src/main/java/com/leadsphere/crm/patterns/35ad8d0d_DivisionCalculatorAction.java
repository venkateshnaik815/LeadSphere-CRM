package com.leadsphere.crm.patterns;

public class DivisionCalculatorAction implements CalculatorAction {
  public static final String DIVISION = "DIVISION";

  @Override
  public String tag() {
    return DIVISION;
  }
}
