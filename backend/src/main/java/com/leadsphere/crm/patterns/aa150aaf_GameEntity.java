package com.leadsphere.crm.patterns;

import com.iluwatar.data.locality.game.component.manager.AiComponentManager;
import com.iluwatar.data.locality.game.component.manager.PhysicsComponentManager;
import com.iluwatar.data.locality.game.component.manager.RenderComponentManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameEntity {

  private final AiComponentManager aiComponentManager;
  private final PhysicsComponentManager physicsComponentManager;
  private final RenderComponentManager renderComponentManager;

  public GameEntity(int numEntities) {
    LOGGER.info("Init Game with #Entity : {}", numEntities);
    aiComponentManager = new AiComponentManager(numEntities);
    physicsComponentManager = new PhysicsComponentManager(numEntities);
    renderComponentManager = new RenderComponentManager(numEntities);
  }

  public void start() {
    LOGGER.info("Start Game");
    aiComponentManager.start();
    physicsComponentManager.start();
    renderComponentManager.start();
  }

  public void update() {
    LOGGER.info("Update Game Component");
    // Process AI.
    aiComponentManager.update();

    // update physics.
    physicsComponentManager.update();

    // Draw to screen.
    renderComponentManager.render();
  }
}
