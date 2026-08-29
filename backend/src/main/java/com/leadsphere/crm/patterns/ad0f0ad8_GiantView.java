package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GiantView {

  public void displayGiant(GiantModel giant) {
    LOGGER.info(giant.toString());
  }
}
