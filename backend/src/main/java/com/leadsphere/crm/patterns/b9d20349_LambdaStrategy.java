package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaStrategy {

  public enum Strategy implements DragonSlayingStrategy {
    MELEE_STRATEGY(() -> LOGGER.info("With your Excalibur you sever the dragon's head!")),
    PROJECTILE_STRATEGY(
        () ->
            LOGGER.info(
                "You shoot the dragon with the magical crossbow and it falls dead on the ground!")),
    SPELL_STRATEGY(
        () ->
            LOGGER.info(
                "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!"));

    private final DragonSlayingStrategy dragonSlayingStrategy;

    Strategy(DragonSlayingStrategy dragonSlayingStrategy) {
      this.dragonSlayingStrategy = dragonSlayingStrategy;
    }

    @Override
    public void execute() {
      dragonSlayingStrategy.execute();
    }
  }
}
