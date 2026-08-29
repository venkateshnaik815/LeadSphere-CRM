package com.leadsphere.crm.patterns;

import org.slf4j.LoggerFactory;

public class GroundDive extends Superpower {

  public GroundDive() {
    super();
    logger = LoggerFactory.getLogger(GroundDive.class);
  }

  @Override
  protected void activate() {
    move(0, 0, -20);
    playSound("GROUNDDIVE_SOUND", 5);
    spawnParticles("GROUNDDIVE_PARTICLE", 20);
  }
}
