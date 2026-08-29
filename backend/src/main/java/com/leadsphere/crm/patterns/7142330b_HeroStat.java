package com.leadsphere.crm.patterns;

import lombok.ToString;
import lombok.Value;

@Value(staticConstructor = "valueOf")
@ToString
class HeroStat {

  int strength;
  int intelligence;
  int luck;
}
