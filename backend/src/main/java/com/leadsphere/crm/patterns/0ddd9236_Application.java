package com.leadsphere.crm.patterns;

import com.iluwatar.data.locality.game.GameEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

  private static final int NUM_ENTITIES = 5;

  public static void main(String[] args) {
    LOGGER.info("Start Game Application using Data-Locality pattern");
    var gameEntity = new GameEntity(NUM_ENTITIES);
    gameEntity.start();
    gameEntity.update();
  }
}
