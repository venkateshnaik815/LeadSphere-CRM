package com.leadsphere.crm.patterns;

import com.iluwatar.throttling.timer.ThrottleTimerImpl;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var callsCount = new CallsCount();
    var human = new BarCustomer("young human", 2, callsCount);
    var dwarf = new BarCustomer("dwarf soldier", 4, callsCount);

    var executorService = Executors.newFixedThreadPool(2);

    executorService.execute(() -> makeServiceCalls(human, callsCount));
    executorService.execute(() -> makeServiceCalls(dwarf, callsCount));

    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
    }
  }

  private static void makeServiceCalls(BarCustomer barCustomer, CallsCount callsCount) {
    var timer = new ThrottleTimerImpl(1000, callsCount);
    var service = new Bartender(timer, callsCount);
    // Sleep is introduced to keep the output in check and easy to view and analyze the results.
    IntStream.range(0, 50)
        .forEach(
            i -> {
              service.orderDrink(barCustomer);
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                LOGGER.error("Thread interrupted: {}", e.getMessage());
              }
            });
  }
}
