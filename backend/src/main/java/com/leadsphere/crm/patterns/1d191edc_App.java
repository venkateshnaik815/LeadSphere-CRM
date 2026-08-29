package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  private static final int GAME_RUNNING_TIME = 2000;

  public static void main(String[] args) {
    try {
      var world = new World();
      var skeleton1 = new Skeleton(1, 10);
      var skeleton2 = new Skeleton(2, 70);
      var statue = new Statue(3, 20);
      world.addEntity(skeleton1);
      world.addEntity(skeleton2);
      world.addEntity(statue);
      world.run();
      Thread.sleep(GAME_RUNNING_TIME);
      world.stop();
    } catch (InterruptedException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
