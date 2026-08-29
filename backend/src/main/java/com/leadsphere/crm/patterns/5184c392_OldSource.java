package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OldSource {
  private static final String VERSION = "1.0";

  public int accumulateSum(int... nums) {
    LOGGER.info("Source module {}", VERSION);
    var sum = 0;
    for (final var num : nums) {
      sum += num;
    }
    return sum;
  }

  public int accumulateMul(int... nums) {
    LOGGER.info("Source module {}", VERSION);
    var sum = 1;
    for (final var num : nums) {
      sum *= num;
    }
    return sum;
  }
}
