package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SimpleTask extends Task {

  @Override
  public void execute() {
    LOGGER.info("Perform some important activity and after call the callback method.");
  }
}
