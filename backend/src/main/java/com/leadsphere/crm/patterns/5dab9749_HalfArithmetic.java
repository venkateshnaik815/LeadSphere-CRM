package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HalfArithmetic {
  private static final String VERSION = "1.5";

  private final HalfSource newSource;
  private final OldSource oldSource;

  public HalfArithmetic(HalfSource newSource, OldSource oldSource) {
    this.newSource = newSource;
    this.oldSource = oldSource;
  }

  public int sum(int... nums) {
    LOGGER.info("Arithmetic sum {}", VERSION);
    return newSource.accumulateSum(nums);
  }

  public int mul(int... nums) {
    LOGGER.info("Arithmetic mul {}", VERSION);
    return oldSource.accumulateMul(nums);
  }

  public boolean ifHasZero(int... nums) {
    LOGGER.info("Arithmetic check zero {}", VERSION);
    return !newSource.ifNonZero(nums);
  }
}
