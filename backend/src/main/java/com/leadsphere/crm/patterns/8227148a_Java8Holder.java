package com.leadsphere.crm.patterns;

import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Java8Holder {

  private Supplier<Heavy> heavy = this::createAndCacheHeavy;

  public Java8Holder() {
    LOGGER.info("Java8Holder created");
  }

  public Heavy getHeavy() {
    return heavy.get();
  }

  private synchronized Heavy createAndCacheHeavy() {
    class HeavyFactory implements Supplier<Heavy> {
      private final Heavy heavyInstance = new Heavy();

      @Override
      public Heavy get() {
        return heavyInstance;
      }
    }

    if (!(heavy instanceof HeavyFactory)) {
      heavy = new HeavyFactory();
    }

    return heavy.get();
  }
}
