package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GameLoop {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  protected volatile GameStatus status;

  protected final GameController controller;

  protected GameLoop() {
    controller = new GameController();
    status = GameStatus.STOPPED;
  }

  public void run() {
    status = GameStatus.RUNNING;
    Thread gameThread = new Thread(this::processGameLoop);
    gameThread.start();
  }

  public void stop() {
    status = GameStatus.STOPPED;
  }

  public boolean isGameRunning() {
    return status == GameStatus.RUNNING;
  }

  protected void processInput() {
    try {
      var lag = new SecureRandom().nextInt(200) + 50;
      Thread.sleep(lag);
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  protected void render() {
    var position = controller.getBulletPosition();
    logger.info("Current bullet position: {}", position);
  }

  protected abstract void processGameLoop();
}
