package com.leadsphere.crm.patterns;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static void main(String[] args) {
    var guardedQueue = new GuardedQueue();
    var executorService = Executors.newFixedThreadPool(3);

    // here we create first thread which is supposed to get from guardedQueue
    executorService.execute(guardedQueue::get);

    // here we wait two seconds to show that the thread which is trying
    // to get from guardedQueue will be waiting
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      LOGGER.error("Error occurred: ", e);
    }
    // now we execute second thread which will put number to guardedQueue
    // and notify first thread that it could get
    executorService.execute(() -> guardedQueue.put(20));
    executorService.shutdown();
    try {
      executorService.awaitTermination(30, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      LOGGER.error("Error occurred: ", e);
    }
  }
}
