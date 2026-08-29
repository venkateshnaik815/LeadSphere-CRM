package com.leadsphere.crm.patterns;

import org.slf4j.Logger;

public abstract class Superpower {

  protected Logger logger;

  protected abstract void activate();

  protected void move(double x, double y, double z) {
    logger.info("Move to ( {}, {}, {} )", x, y, z);
  }

  protected void playSound(String soundName, int volume) {
    logger.info("Play {} with volume {}", soundName, volume);
  }

  protected void spawnParticles(String particleType, int count) {
    logger.info("Spawn {} particle with type {}", count, particleType);
  }
}
