package com.leadsphere.crm.patterns;

import java.io.Closeable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TreasureChest implements Closeable {

  public TreasureChest() {
    LOGGER.info("Treasure chest opens.");
  }

  @Override
  public void close() {
    LOGGER.info("Treasure chest closes.");
  }
}
