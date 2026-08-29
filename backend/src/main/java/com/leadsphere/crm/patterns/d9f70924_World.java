package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class World {

  protected List<Entity> entities;

  protected volatile boolean isRunning;

  public World() {
    entities = new ArrayList<>();
    isRunning = false;
  }

  private void gameLoop() {
    while (isRunning) {
      processInput();
      update();
      render();
    }
  }

  private void processInput() {
    try {
      int lag = new SecureRandom().nextInt(200) + 50;
      Thread.sleep(lag);
    } catch (InterruptedException e) {
      LOGGER.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  private void update() {
    for (var entity : entities) {
      entity.update();
    }
  }

  private void render() {
    // Does Nothing
  }

  public void run() {
    LOGGER.info("Start game.");
    isRunning = true;
    var thread = new Thread(this::gameLoop);
    thread.start();
  }

  public void stop() {
    LOGGER.info("Stop game.");
    isRunning = false;
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }
}
