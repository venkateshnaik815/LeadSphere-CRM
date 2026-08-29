package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RuleEngine<T> {

  private final List<Rule<T>> rules;

  public RuleEngine(List<Rule<T>> rules) {
    this.rules = List.copyOf(rules);
  }

  public RuleEngineResult run(T context) {
    Objects.requireNonNull(context, "context must not be null");
    List<String> passed = new ArrayList<>();
    List<String> failed = new ArrayList<>();
    for (Rule<T> rule : rules) {
      if (rule.evaluate(context)) {
        rule.execute(context);
        passed.add(rule.name());
      } else {
        failed.add(rule.name());
      }
    }
    return new RuleEngineResult(failed.isEmpty(), passed, failed);
  }

  public List<Rule<T>> rules() {
    return rules;
  }
}
