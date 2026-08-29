package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinimumAgeRule implements Rule<LoanApplication> {

  private final int minimumAge;

  public MinimumAgeRule(int minimumAge) {
    this.minimumAge = minimumAge;
  }

  @Override
  public String name() {
    return "MinimumAgeRule";
  }

  @Override
  public boolean evaluate(LoanApplication context) {
    return context.age() >= minimumAge;
  }

  @Override
  public void execute(LoanApplication context) {
    LOGGER.info("Applicant age {} meets the minimum age of {}.", context.age(), minimumAge);
  }
}
