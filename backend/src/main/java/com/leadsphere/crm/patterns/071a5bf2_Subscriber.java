package com.leadsphere.crm.patterns;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

@Slf4j
public class Subscriber extends BaseSubscriber<Integer> {

  @Override
  protected void hookOnSubscribe(@NonNull Subscription subscription) {
    request(10); // request 10 items initially
  }

  @Override
  protected void hookOnNext(@NonNull Integer value) {
    processItem();
    LOGGER.info("process({})", value);
    if (value % 5 == 0) {
      // request for the next 5 items after processing first 5
      request(5);
    }
  }

  @Override
  protected void hookOnComplete() {
    App.latch.countDown();
  }

  private void processItem() {
    try {
      Thread.sleep(500); // simulate slow processing
    } catch (InterruptedException e) {
      LOGGER.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
  }
}
