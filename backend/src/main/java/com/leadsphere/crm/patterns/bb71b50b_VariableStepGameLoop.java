package com.leadsphere.crm.patterns;

public class VariableStepGameLoop extends GameLoop {

  @Override
  protected void processGameLoop() {
    var lastFrameTime = System.currentTimeMillis();
    while (isGameRunning()) {
      processInput();
      var currentFrameTime = System.currentTimeMillis();
      var elapsedTime = currentFrameTime - lastFrameTime;
      update(elapsedTime);
      lastFrameTime = currentFrameTime;
      render();
    }
  }

  protected void update(Long elapsedTime) {
    controller.moveBullet(0.5f * elapsedTime / 1000);
  }
}
