package com.leadsphere.crm.patterns;

public record InvoiceGenerator(double amount, TaxCalculator taxCalculator) {
  public double getAmountWithTax() {
    return amount + taxCalculator.calculate(amount);
  }
}
