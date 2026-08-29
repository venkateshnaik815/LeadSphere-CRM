package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    try (var ignored = new SlidingDoor()) {
      LOGGER.info("Walking in.");
    }

    try (var ignored = new TreasureChest()) {
      LOGGER.info("Looting contents.");
    }
  }
}
