package com.leadsphere.crm.patterns;

import com.iluwatar.data.locality.game.component.AiComponent;
import com.iluwatar.data.locality.game.component.Component;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AiComponentManager {

  private static final int MAX_ENTITIES = 10000;

  private final int numEntities;

  private final Component[] aiComponents = new AiComponent[MAX_ENTITIES];

  public AiComponentManager(int numEntities) {
    this.numEntities = numEntities;
  }

  public void start() {
    LOGGER.info("Start AI Game Component");
    IntStream.range(0, numEntities).forEach(i -> aiComponents[i] = new AiComponent());
  }

  public void update() {
    LOGGER.info("Update AI Game Component");
    IntStream.range(0, numEntities)
        .filter(i -> aiComponents.length > i && aiComponents[i] != null)
        .forEach(i -> aiComponents[i].update());
  }
}
