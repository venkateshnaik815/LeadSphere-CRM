package com.leadsphere.crm.patterns;

import org.slf4j.LoggerFactory;

public class SkyLaunch extends Superpower {

  public SkyLaunch() {
    super();
    logger = LoggerFactory.getLogger(SkyLaunch.class);
  }

  @Override
  protected void activate() {
    move(0, 0, 20);
    playSound("SKYLAUNCH_SOUND", 1);
    spawnParticles("SKYLAUNCH_PARTICLE", 100);
  }
}
