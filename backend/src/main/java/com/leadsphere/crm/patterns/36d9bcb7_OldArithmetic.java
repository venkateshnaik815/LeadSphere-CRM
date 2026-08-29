package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OldArithmetic {
  private static final String VERSION = "1.0";

  private final OldSource source;

  public OldArithmetic(OldSource source) {
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
}
