package com.leadsphere.crm.patterns;

import com.iluwatar.delegation.simple.Printer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HpPrinter implements Printer {

  @Override
  public void print(String message) {
    LOGGER.info("HP Printer : {}", message);
  }
}
