package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  private static final int NUMBER_OF_THREADS = 5;
  private static final int BASE_AMOUNT = 1000;
  private static final int ACCOUNT_NUM = 4;

  public static void runner(Bank bank, CountDownLatch latch) {
    try {
      SecureRandom random = new SecureRandom();
      Thread.sleep(random.nextInt(1000));
      LOGGER.info("Start transferring...");
      for (int i = 0; i < 1000000; i++) {
        bank.transfer(random.nextInt(4), random.nextInt(4), random.nextInt(0, BASE_AMOUNT));
      }
      LOGGER.info("Finished transferring.");
      latch.countDown();
    } catch (InterruptedException e) {
      LOGGER.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    var bank = new Bank(ACCOUNT_NUM, BASE_AMOUNT);
    var latch = new CountDownLatch(NUMBER_OF_THREADS);
    var executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    for (int i = 0; i < NUMBER_OF_THREADS; i++) {
      executorService.execute(() -> runner(bank, latch));
    }

    latch.await();
  }
}
