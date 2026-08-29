package com.leadsphere.crm.patterns;

import com.iluwatar.data.locality.game.component.Component;
import com.iluwatar.data.locality.game.component.RenderComponent;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RenderComponentManager {

  private static final int MAX_ENTITIES = 10000;

  private final int numEntities;

  private final Component[] renderComponents = new RenderComponent[MAX_ENTITIES];

  public RenderComponentManager(int numEntities) {
    this.numEntities = numEntities;
  }

  public void start() {
    LOGGER.info("Start Render Game Component ");
    IntStream.range(0, numEntities).forEach(i -> renderComponents[i] = new RenderComponent());
  }

  public void render() {
    LOGGER.info("Update Render Game Component ");
    // Process Render.
    IntStream.range(0, numEntities)
        .filter(i -> renderComponents.length > i && renderComponents[i] != null)
        .forEach(i -> renderComponents[i].render());
  }
}
