package com.leadsphere.crm.patterns;

import static java.lang.Thread.sleep;

import com.iluwatar.ambassador.util.RandomProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteService implements RemoteServiceInterface {
  private static final int THRESHOLD = 200;
  private static RemoteService service = null;
  private final RandomProvider randomProvider;

  static synchronized RemoteService getRemoteService() {
    if (service == null) {
      service = new RemoteService();
    }
    return service;
  }

  private RemoteService() {
    this(Math::random);
  }

  RemoteService(RandomProvider randomProvider) {
    this.randomProvider = randomProvider;
  }

  @Override
  public long doRemoteFunction(int value) {

    long waitTime = (long) Math.floor(randomProvider.random() * 1000);

    try {
      sleep(waitTime);
    } catch (InterruptedException e) {
      LOGGER.error("Thread sleep state interrupted", e);
      Thread.currentThread().interrupt();
    }
    return waitTime <= THRESHOLD
        ? value * 10
        : RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
  }
}
