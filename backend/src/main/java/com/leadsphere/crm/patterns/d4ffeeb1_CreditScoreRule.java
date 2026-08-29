package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditScoreRule implements Rule<LoanApplication> {

  private final int minimumScore;

  public CreditScoreRule(int minimumScore) {
    this.minimumScore = minimumScore;
  }

  @Override
  public String name() {
    return "CreditScoreRule";
  }

  @Override
  public boolean evaluate(LoanApplication context) {
    return context.creditScore() >= minimumScore;
  }

  @Override
  public void execute(LoanApplication context) {
    LOGGER.info(
        "Applicant credit score {} meets the minimum score of {}.",
        context.creditScore(),
        minimumScore);
  }
}
