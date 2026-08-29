package com.leadsphere.crm.patterns;

public class MultiplicationCalculatorAction implements CalculatorAction {
  public static final String MULTIPLICATION = "MULTIPLICATION";

  @Override
  public String tag() {
    return MULTIPLICATION;
  }
}
