package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolderThreadSafe {

  private Heavy heavy;

  public HolderThreadSafe() {
    LOGGER.info("HolderThreadSafe created");
  }

  public synchronized Heavy getHeavy() {
    if (heavy == null) {
      heavy = new Heavy();
    }
    return heavy;
  }
}
