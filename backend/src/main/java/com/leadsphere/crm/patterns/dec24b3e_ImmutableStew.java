package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImmutableStew {

  private final StewData data;

  public ImmutableStew(int numPotatoes, int numCarrots, int numMeat, int numPeppers) {
    data = new StewData(numPotatoes, numCarrots, numMeat, numPeppers);
  }

  public void mix() {
    LOGGER.info(
        "Mixing the immutable stew we find: {} potatoes, {} carrots, {} meat and {} peppers",
        data.numPotatoes(),
        data.numCarrots(),
        data.numMeat(),
        data.numPeppers());
  }
}
