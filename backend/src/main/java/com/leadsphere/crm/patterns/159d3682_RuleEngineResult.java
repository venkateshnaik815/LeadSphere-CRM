package com.leadsphere.crm.patterns;

import java.util.List;

public record RuleEngineResult(
    boolean approved, List<String> passedRules, List<String> failedRules) {

  public RuleEngineResult {
    passedRules = List.copyOf(passedRules);
    failedRules = List.copyOf(failedRules);
  }
}
