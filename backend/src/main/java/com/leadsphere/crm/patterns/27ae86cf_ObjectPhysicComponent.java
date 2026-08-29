package com.leadsphere.crm.patterns;

import com.iluwatar.component.GameObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectPhysicComponent implements PhysicComponent {

  @Override
  public void update(GameObject gameObject) {
    gameObject.updateCoordinate();
    LOGGER.info(gameObject.getName() + "'s coordinate has been changed.");
  }
}
