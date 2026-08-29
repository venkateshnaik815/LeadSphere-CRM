package com.leadsphere.crm.patterns;

import com.iluwatar.lockableobject.domain.Creature;
import com.iluwatar.lockableobject.domain.Elf;
import com.iluwatar.lockableobject.domain.Feind;
import com.iluwatar.lockableobject.domain.Human;
import com.iluwatar.lockableobject.domain.Orc;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App implements Runnable {

  private static final int WAIT_TIME = 3;
  private static final int WORKERS = 2;
  private static final int MULTIPLICATION_FACTOR = 3;

  public static void main(String[] args) {
    var app = new App();
    app.run();
  }

  @Override
  public void run() {
    // The target object for this example.
    var sword = new SwordOfAragorn();
    // Creation of creatures.
    List<Creature> creatures = new ArrayList<>();
    for (var i = 0; i < WORKERS; i++) {
      creatures.add(new Elf(String.format("Elf %s", i)));
      creatures.add(new Orc(String.format("Orc %s", i)));
      creatures.add(new Human(String.format("Human %s", i)));
    }
    int totalFiends = WORKERS * MULTIPLICATION_FACTOR;
    ExecutorService service = Executors.newFixedThreadPool(totalFiends);
    // Attach every creature and the sword is a Fiend to fight for the sword.
    for (var i = 0; i < totalFiends; i = i + MULTIPLICATION_FACTOR) {
      service.submit(new Feind(creatures.get(i), sword));
      service.submit(new Feind(creatures.get(i + 1), sword));
      service.submit(new Feind(creatures.get(i + 2), sword));
    }
    // Wait for program to terminate.
    try {
      if (!service.awaitTermination(WAIT_TIME, TimeUnit.SECONDS)) {
        LOGGER.info("The master of the sword is now {}.", sword.getLocker().getName());
      }
    } catch (InterruptedException e) {
      LOGGER.error(e.getMessage());
      Thread.currentThread().interrupt();
    } finally {
      service.shutdown();
    }
  }
}
