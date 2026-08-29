package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhysicsComponent implements Component {

  @Override
  public void update() {
    LOGGER.info("Update physics component of game");
  }

  @Override
  public void render() {
    // do nothing
  }
}
