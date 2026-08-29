package com.leadsphere.crm.patterns;

import com.iluwatar.component.GameObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoInputComponent implements InputComponent {
  private static final int WALK_ACCELERATION = 2;

  @Override
  public void update(GameObject gameObject, int e) {
    gameObject.updateVelocity(WALK_ACCELERATION);
    LOGGER.info(gameObject.getName() + " has moved right.");
  }
}
