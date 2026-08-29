package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewArithmetic {
  private static final String VERSION = "2.0";

  private final NewSource source;

  public NewArithmetic(NewSource source) {
    this.source = source;
  }

  public int sum(int... nums) {
    LOGGER.info("Arithmetic sum {}", VERSION);
    return source.accumulateSum(nums);
  }

  public int mul(int... nums) {
    LOGGER.info("Arithmetic mul {}", VERSION);
    return source.accumulateMul(nums);
  }

  public boolean ifHasZero(int... nums) {
    LOGGER.info("Arithmetic check zero {}", VERSION);
    return !source.ifNonZero(nums);
  }
}
