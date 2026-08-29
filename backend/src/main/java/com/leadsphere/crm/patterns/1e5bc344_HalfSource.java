package com.leadsphere.crm.patterns;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HalfSource {
  private static final String VERSION = "1.5";

  public int accumulateSum(int... nums) {
    LOGGER.info("Source module {}", VERSION);
    return Arrays.stream(nums).reduce(0, Integer::sum);
  }

  public boolean ifNonZero(int... nums) {
    LOGGER.info("Source module {}", VERSION);
    return Arrays.stream(nums).allMatch(num -> num != 0);
  }
}
