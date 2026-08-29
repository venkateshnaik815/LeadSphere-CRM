package com.leadsphere.crm.patterns;

import com.iluwatar.data.locality.game.component.Component;
import com.iluwatar.data.locality.game.component.PhysicsComponent;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhysicsComponentManager {

  private static final int MAX_ENTITIES = 10000;

  private final int numEntities;

  private final Component[] physicsComponents = new PhysicsComponent[MAX_ENTITIES];

  public PhysicsComponentManager(int numEntities) {
    this.numEntities = numEntities;
  }

  public void start() {
    LOGGER.info("Start Physics Game Component ");
    IntStream.range(0, numEntities).forEach(i -> physicsComponents[i] = new PhysicsComponent());
  }

  public void update() {
    LOGGER.info("Update Physics Game Component ");
    // Process physics.
    IntStream.range(0, numEntities)
        .filter(i -> physicsComponents.length > i && physicsComponents[i] != null)
        .forEach(i -> physicsComponents[i].update());
  }
}
