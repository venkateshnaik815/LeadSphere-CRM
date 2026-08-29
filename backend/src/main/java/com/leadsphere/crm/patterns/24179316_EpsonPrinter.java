package com.leadsphere.crm.patterns;

import com.iluwatar.delegation.simple.Printer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EpsonPrinter implements Printer {

  @Override
  public void print(String message) {
    LOGGER.info("Epson Printer : {}", message);
  }
}
