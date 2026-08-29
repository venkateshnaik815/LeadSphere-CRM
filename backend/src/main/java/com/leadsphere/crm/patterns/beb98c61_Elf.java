package com.leadsphere.crm.patterns;

public class Elf extends Creature {

  public Elf(String name) {
    super(name);
    setType(CreatureType.ELF);
    setDamage(CreatureStats.ELF_DAMAGE.getValue());
    setHealth(CreatureStats.ELF_HEALTH.getValue());
  }
}
