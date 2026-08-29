package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    var warrior =
        CharacterStepBuilder.newBuilder()
            .name("Amberjill")
            .fighterClass("Paladin")
            .withWeapon("Sword")
            .noAbilities()
            .build();

    LOGGER.info(warrior.toString());

    var mage =
        CharacterStepBuilder.newBuilder()
            .name("Riobard")
            .wizardClass("Sorcerer")
            .withSpell("Fireball")
            .withAbility("Fire Aura")
            .withAbility("Teleport")
            .noMoreAbilities()
            .build();

    LOGGER.info(mage.toString());

    var thief =
        CharacterStepBuilder.newBuilder().name("Desmond").fighterClass("Rogue").noWeapon().build();

    LOGGER.info(thief.toString());
  }
}
