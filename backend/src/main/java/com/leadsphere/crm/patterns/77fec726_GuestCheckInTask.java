package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class GuestCheckInTask implements Runnable {

  private final String guestName;

  @Override
  public void run() {
    String employeeName = Thread.currentThread().getName();
    LOGGER.info("{} is checking in {}...", employeeName, guestName);
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOGGER.error("Check-in for {} was interrupted", guestName);
    }
    LOGGER.info("{} has been successfully checked in!", guestName);
  }
}
