package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var statA = HeroStat.valueOf(10, 5, 0);
    var statB = HeroStat.valueOf(10, 5, 0);
    var statC = HeroStat.valueOf(5, 1, 8);

    LOGGER.info("statA: {}", statA);
    LOGGER.info("statB: {}", statB);
    LOGGER.info("statC: {}", statC);

    LOGGER.info("Are statA and statB equal? {}", statA.equals(statB));
    LOGGER.info("Are statA and statC equal? {}", statA.equals(statC));
  }
}
