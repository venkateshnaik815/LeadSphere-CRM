package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolderNaive {

  private Heavy heavy;

  public HolderNaive() {
    LOGGER.info("HolderNaive created");
  }

  public Heavy getHeavy() {
    if (heavy == null) {
      heavy = new Heavy();
    }
    return heavy;
  }
}
