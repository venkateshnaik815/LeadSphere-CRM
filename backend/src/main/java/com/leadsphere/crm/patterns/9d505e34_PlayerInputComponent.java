package com.leadsphere.crm.patterns;

import com.iluwatar.component.GameObject;
import java.awt.event.KeyEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerInputComponent implements InputComponent {
  private static final int WALK_ACCELERATION = 1;

  @Override
  public void update(GameObject gameObject, int e) {
    switch (e) {
      case KeyEvent.KEY_LOCATION_LEFT -> {
        gameObject.updateVelocity(-WALK_ACCELERATION);
        LOGGER.info(gameObject.getName() + " has moved left.");
      }
      case KeyEvent.KEY_LOCATION_RIGHT -> {
        gameObject.updateVelocity(WALK_ACCELERATION);
        LOGGER.info(gameObject.getName() + " has moved right.");
      }
      default -> {
        LOGGER.info(gameObject.getName() + "'s velocity is unchanged due to the invalid input");
        gameObject.updateVelocity(0);
      } // incorrect input
    }
  }
}
