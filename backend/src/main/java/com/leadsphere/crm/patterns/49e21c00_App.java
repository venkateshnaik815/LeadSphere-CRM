package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var engine =
        new RuleEngine<>(
            List.of(
                new MinimumAgeRule(18), new MinimumIncomeRule(2000.0), new CreditScoreRule(650)));

    var approved = new LoanApplication(30, 3500.0, 15000.0, 720);
    var rejected = new LoanApplication(17, 1500.0, 15000.0, 720);

    report(engine.run(approved));
    report(engine.run(rejected));
  }

  private static void report(RuleEngineResult result) {
    if (result.approved()) {
      LOGGER.info("Loan approved. Passed rules: {}", result.passedRules());
    } else {
      LOGGER.info("Loan rejected. Failed rules: {}", result.failedRules());
    }
  }
}
