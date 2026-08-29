package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SquareNumberRequest {

  private final Long number;

  public void delayedSquaring(final Consumer consumer) {

    var minTimeOut = 5000L;

    SecureRandom secureRandom = new SecureRandom();
    var randomTimeOut = secureRandom.nextInt(2000);

    try {
      // this will make the thread sleep from 5-7s.
      Thread.sleep(minTimeOut + randomTimeOut);
    } catch (InterruptedException e) {
      LOGGER.error("Exception while sleep ", e);
      Thread.currentThread().interrupt();
    } finally {
      consumer.add(number * number);
    }
  }
}
