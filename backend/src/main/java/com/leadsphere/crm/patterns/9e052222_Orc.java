package com.leadsphere.crm.patterns;

public class Orc extends Creature {
  public Orc(String name) {
    super(name);
    setType(CreatureType.ORC);
    setDamage(CreatureStats.ORC_DAMAGE.getValue());
    setHealth(CreatureStats.ORC_HEALTH.getValue());
  }
}
