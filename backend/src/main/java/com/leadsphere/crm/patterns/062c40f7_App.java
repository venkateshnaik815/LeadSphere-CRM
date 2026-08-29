package com.leadsphere.crm.patterns;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    var queue = new ItemQueue();

    var executorService = Executors.newFixedThreadPool(5);
    for (var i = 0; i < 2; i++) {

      final var producer = new Producer("Producer_" + i, queue);
      executorService.submit(
          () -> {
            while (true) {
              producer.produce();
            }
          });
    }

    for (var i = 0; i < 3; i++) {
      final var consumer = new Consumer("Consumer_" + i, queue);
      executorService.submit(
          () -> {
            while (true) {
              consumer.consume();
            }
          });
    }

    executorService.shutdown();
    try {
      executorService.awaitTermination(10, TimeUnit.SECONDS);
      executorService.shutdownNow();
    } catch (InterruptedException e) {
      LOGGER.error("Error waiting for ExecutorService shutdown");
    }
  }
}
