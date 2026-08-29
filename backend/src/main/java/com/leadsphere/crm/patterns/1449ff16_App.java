package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    FrontDeskService frontDesk = new FrontDeskService(5);
    LOGGER.info("Hotel front desk operation started!");

    LOGGER.info("Processing 30 regular guest check-ins...");
    for (int i = 1; i <= 30; i++) {
      frontDesk.submitGuestCheckIn(new GuestCheckInTask("Guest-" + i));
      Thread.sleep(100);
    }

    LOGGER.info("Processing 3 VIP guest check-ins...");
    List<Future<String>> vipResults = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      Future<String> result =
          frontDesk.submitVipGuestCheckIn(new VipGuestCheckInTask("VIP-Guest-" + i));
      vipResults.add(result);
    }

    frontDesk.shutdown();

    if (frontDesk.awaitTermination(1, TimeUnit.HOURS)) {
      LOGGER.info("VIP Check-in Results:");
      for (Future<String> result : vipResults) {
        LOGGER.info(result.get());
      }
      LOGGER.info("All guests have been successfully checked in. Front desk is now closed.");
    } else {
      LOGGER.warn("Check-in timeout. Forcefully shutting down the front desk.");
    }
  }
}
