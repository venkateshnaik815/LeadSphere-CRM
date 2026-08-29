package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AiComponent implements Component {

  @Override
  public void update() {
    LOGGER.info("update AI component");
  }

  @Override
  public void render() {
    // Do Nothing.
  }
}
