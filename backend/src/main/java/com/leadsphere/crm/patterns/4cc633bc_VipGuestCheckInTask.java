package com.leadsphere.crm.patterns;

import java.util.concurrent.Callable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class VipGuestCheckInTask implements Callable<String> {

  private final String vipGuestName;

  @Override
  public String call() throws Exception {
    String employeeName = Thread.currentThread().getName();
    LOGGER.info("{} is checking in VIP guest {}...", employeeName, vipGuestName);

    Thread.sleep(1000);

    String result = vipGuestName + " has been successfully checked in!";
    LOGGER.info("VIP check-in completed: {}", result);
    return result;
  }
}
