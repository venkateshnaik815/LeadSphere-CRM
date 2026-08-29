package com.leadsphere.crm.patterns;

import java.util.HashMap;

public class App {
  public static void main(String[] args) {
    // create some weapons
    var enchantedHammer = new Weapon(1, "enchanted hammer");
    var brokenGreatSword = new Weapon(2, "broken great sword");
    var silverTrident = new Weapon(3, "silver trident");

    // create repository
    var weaponRepository = new ArmsDealer(new HashMap<>(), new WeaponDatabase());

    // perform operations on the weapons
    weaponRepository.registerNew(enchantedHammer);
    weaponRepository.registerModified(silverTrident);
    weaponRepository.registerDeleted(brokenGreatSword);
    weaponRepository.commit();
  }
}
