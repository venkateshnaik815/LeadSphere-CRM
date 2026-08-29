package com.leadsphere.crm.patterns;

import java.awt.event.KeyEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class App {
  public static void main(String[] args) {
    final var player = GameObject.createPlayer();
    final var npc = GameObject.createNpc();

    LOGGER.info("Player Update:");
    player.update(KeyEvent.KEY_LOCATION_LEFT);
    LOGGER.info("NPC Update:");
    npc.demoUpdate();
  }
}
