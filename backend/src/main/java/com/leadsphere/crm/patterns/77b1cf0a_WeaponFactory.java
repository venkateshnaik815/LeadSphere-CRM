package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface WeaponFactory {

  Weapon create(WeaponType name);

  static WeaponFactory factory(Consumer<Builder> consumer) {
    var map = new HashMap<WeaponType, Supplier<Weapon>>();
    consumer.accept(map::put);
    return name -> map.get(name).get();
  }
}
