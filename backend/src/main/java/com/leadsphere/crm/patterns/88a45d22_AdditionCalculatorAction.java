package com.leadsphere.crm.patterns;

public class AdditionCalculatorAction implements CalculatorAction {
  public static final String ADDITION = "ADDITION";

  @Override
  public String tag() {
    return ADDITION;
  }
}
