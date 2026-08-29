package com.leadsphere.crm.patterns;

public class FixedStepGameLoop extends GameLoop {

  private static final long MS_PER_FRAME = 20;

  @Override
  protected void processGameLoop() {
    var previousTime = System.currentTimeMillis();
    var lag = 0L;
    while (isGameRunning()) {
      var currentTime = System.currentTimeMillis();
      var elapsedTime = currentTime - previousTime;
      previousTime = currentTime;
      lag += elapsedTime;

      processInput();

      while (lag >= MS_PER_FRAME) {
        update();
        lag -= MS_PER_FRAME;
      }

      render();
    }
  }

  protected void update() {
    controller.moveBullet(0.5f * MS_PER_FRAME / 1000);
  }
}
