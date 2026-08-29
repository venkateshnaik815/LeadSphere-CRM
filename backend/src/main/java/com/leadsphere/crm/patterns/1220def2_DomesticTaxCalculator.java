package com.leadsphere.crm.patterns;

import com.iluwatar.separatedinterface.invoice.TaxCalculator;

public class DomesticTaxCalculator implements TaxCalculator {

  public static final double TAX_PERCENTAGE = 20;

  @Override
  public double calculate(double amount) {
    return amount * TAX_PERCENTAGE / 100.0;
  }
}
