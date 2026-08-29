package com.leadsphere.crm.patterns;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    final var inventory = new Inventory(1000);
    var executorService = Executors.newFixedThreadPool(3);
    IntStream.range(0, 3)
        .<Runnable>mapToObj(
            i ->
                () -> {
                  while (inventory.addItem(new Item())) {
                    LOGGER.info("Adding another item");
                  }
                })
        .forEach(executorService::execute);

    executorService.shutdown();
    try {
      executorService.awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      LOGGER.error("Error waiting for ExecutorService shutdown");
      Thread.currentThread().interrupt();
    }
  }
}
