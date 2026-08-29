package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GameItem {

  public void draw() {
    LOGGER.info("draw");
    doDraw();
  }

  public abstract void doDraw();

  public abstract void click();
}
