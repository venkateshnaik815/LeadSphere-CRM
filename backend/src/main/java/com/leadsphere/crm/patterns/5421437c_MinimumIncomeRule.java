package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinimumIncomeRule implements Rule<LoanApplication> {

  private final double minimumIncome;

  public MinimumIncomeRule(double minimumIncome) {
    this.minimumIncome = minimumIncome;
  }

  @Override
  public String name() {
    return "MinimumIncomeRule";
  }

  @Override
  public boolean evaluate(LoanApplication context) {
    return context.monthlyIncome() >= minimumIncome;
  }

  @Override
  public void execute(LoanApplication context) {
    LOGGER.info(
        "Applicant income {} meets the minimum income of {}.",
        context.monthlyIncome(),
        minimumIncome);
  }
}
