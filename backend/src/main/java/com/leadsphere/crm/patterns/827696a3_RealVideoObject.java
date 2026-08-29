
package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class RealVideoObject implements ExpensiveObject {

  public RealVideoObject() {
    heavyInitialConfiguration();
  }

  private void heavyInitialConfiguration() {
    LOGGER.info("Loading initial video configurations...");
  }

  @Override
  public void process() {
    LOGGER.info("Processing and playing video content...");
  }
}
