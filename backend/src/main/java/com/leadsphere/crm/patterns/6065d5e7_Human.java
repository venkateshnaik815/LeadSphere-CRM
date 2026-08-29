package com.leadsphere.crm.patterns;

public class Human extends Creature {

  public Human(String name) {
    super(name);
    setType(CreatureType.HUMAN);
    setDamage(CreatureStats.HUMAN_DAMAGE.getValue());
    setHealth(CreatureStats.HUMAN_HEALTH.getValue());
  }
}
