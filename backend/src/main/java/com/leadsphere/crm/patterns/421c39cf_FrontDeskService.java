package com.leadsphere.crm.patterns;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FrontDeskService {

  private final ExecutorService executorService;
  private final int numberOfEmployees;

  public FrontDeskService(int numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
    this.executorService = Executors.newFixedThreadPool(numberOfEmployees);
    LOGGER.info("Front desk initialized with {} employees.", numberOfEmployees);
  }

  public Future<Void> submitGuestCheckIn(Runnable task) {
    LOGGER.debug("Submitting regular guest check-in task");
    return executorService.submit(task, null);
  }

  public <T> Future<T> submitVipGuestCheckIn(Callable<T> task) {
    LOGGER.debug("Submitting VIP guest check-in task");
    return executorService.submit(task);
  }

  public void shutdown() {
    LOGGER.info("Front desk is closing - no new guests will be accepted.");
    executorService.shutdown();
  }

  public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
    LOGGER.info("Waiting for all check-ins to complete (max wait: {} {})", timeout, unit);
    return executorService.awaitTermination(timeout, unit);
  }

  public int getNumberOfEmployees() {
    return numberOfEmployees;
  }
}
