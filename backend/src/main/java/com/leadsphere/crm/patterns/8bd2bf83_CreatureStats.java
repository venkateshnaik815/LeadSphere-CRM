package com.leadsphere.crm.patterns;

import lombok.Getter;

public enum CreatureStats {
  ELF_HEALTH(90),
  ELF_DAMAGE(40),
  ORC_HEALTH(70),
  ORC_DAMAGE(50),
  HUMAN_HEALTH(60),
  HUMAN_DAMAGE(60);

  @Getter final int value;

  CreatureStats(int value) {
    this.value = value;
  }
}
