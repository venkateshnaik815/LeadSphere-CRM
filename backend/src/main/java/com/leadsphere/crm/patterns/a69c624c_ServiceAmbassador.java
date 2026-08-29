package com.leadsphere.crm.patterns;

import static com.iluwatar.ambassador.RemoteServiceStatus.FAILURE;
import static java.lang.Thread.sleep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceAmbassador implements RemoteServiceInterface {

  private static final int RETRIES = 3;
  private static final int DELAY_MS = 3000;

  ServiceAmbassador() {}

  @Override
  public long doRemoteFunction(int value) {
    return safeCall(value);
  }

  private long checkLatency(int value) {
    var startTime = System.currentTimeMillis();
    var result = RemoteService.getRemoteService().doRemoteFunction(value);
    var timeTaken = System.currentTimeMillis() - startTime;

    LOGGER.info("Time taken (ms): {}", timeTaken);
    return result;
  }

  private long safeCall(int value) {
    var retries = 0;
    var result = FAILURE.getRemoteServiceStatusValue();

    for (int i = 0; i < RETRIES; i++) {
      if (retries >= RETRIES) {
        return FAILURE.getRemoteServiceStatusValue();
      }

      if ((result = checkLatency(value)) == FAILURE.getRemoteServiceStatusValue()) {
        LOGGER.info("Failed to reach remote: ({})", i + 1);
        retries++;
        try {
          sleep(DELAY_MS);
        } catch (InterruptedException e) {
          LOGGER.error("Thread sleep state interrupted", e);
          Thread.currentThread().interrupt();
        }
      } else {
        break;
      }
    }
    return result;
  }
}
