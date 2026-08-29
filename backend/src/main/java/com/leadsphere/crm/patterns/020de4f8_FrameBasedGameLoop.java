package com.leadsphere.crm.patterns;

public class FrameBasedGameLoop extends GameLoop {

  @Override
  protected void processGameLoop() {
    while (isGameRunning()) {
      processInput();
      update();
      render();
    }
  }

  protected void update() {
    controller.moveBullet(0.5f);
  }
}
