package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatapultView implements View {

  @Override
  public void display() {
    LOGGER.info("Displaying catapults");
  }
}
