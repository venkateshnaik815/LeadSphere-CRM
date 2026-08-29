package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var factory =
        WeaponFactory.factory(
            builder -> {
              builder.add(WeaponType.SWORD, Sword::new);
              builder.add(WeaponType.AXE, Axe::new);
              builder.add(WeaponType.SPEAR, Spear::new);
              builder.add(WeaponType.BOW, Bow::new);
            });
    var list = new ArrayList<Weapon>();
    list.add(factory.create(WeaponType.AXE));
    list.add(factory.create(WeaponType.SPEAR));
    list.add(factory.create(WeaponType.SWORD));
    list.add(factory.create(WeaponType.BOW));
    list.forEach(weapon -> LOGGER.info("{}", weapon.toString()));
  }
}
