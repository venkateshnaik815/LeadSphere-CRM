package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Setter
@Getter
@Slf4j
public class Wizard {

  private int health;
  private int agility;
  private int wisdom;
  private int numberOfPlayedSounds;
  private int numberOfSpawnedParticles;

  public void playSound() {
    LOGGER.info("Playing sound");
    numberOfPlayedSounds++;
  }

  public void spawnParticles() {
    LOGGER.info("Spawning particles");
    numberOfSpawnedParticles++;
  }
}
