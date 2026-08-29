package com.leadsphere.crm.patterns;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public void run() {
    final var executorService = Executors.newSingleThreadScheduledExecutor();
    try {
      executorService.scheduleAtFixedRate(
          new Runnable() {
            final World world = new World();

            @Override
            public void run() {
              var countries = world.fetch();
              LOGGER.info("Our world currently has the following countries:-");
              countries.stream().map(country -> "\t" + country).forEach(LOGGER::info);
            }
          },
          0,
          15,
          TimeUnit.SECONDS);

      // Keep running for 45 seconds before shutdown (for demo purpose)
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOGGER.error("Thread was interrupted", e);
    } finally {
      executorService.shutdown();
    }
  }

  public static void main(String[] args) {
    var app = new App();
    app.run();
  }
}
