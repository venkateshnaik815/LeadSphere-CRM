package com.leadsphere.crm.patterns;

import com.iluwatar.component.GameObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectGraphicComponent implements GraphicComponent {

  @Override
  public void update(GameObject gameObject) {
    LOGGER.info(gameObject.getName() + "'s current velocity: " + gameObject.getVelocity());
  }
}
