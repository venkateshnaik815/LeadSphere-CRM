package com.leadsphere.crm.patterns;

import lombok.Data;
import lombok.Getter;

@Data
public final class SetVariableCalculatorAction implements CalculatorAction {

  public static final String SET_VARIABLE = "SET_VARIABLE";

  @Getter private final Double variable;

  @Override
  public String tag() {
    return SET_VARIABLE;
  }
}
