package com.leadsphere.crm.patterns;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PACKAGE)
public class Candy {

  enum Type {
    CRUSHABLE_CANDY,
    REWARD_FRUIT
  }

  String name;
  Candy parent;
  String parentName;

  @Setter private int points;
  private final Type type;

  Candy(String name, String parentName, Type type, int points) {
    this.name = name;
    this.parent = null;
    this.type = type;
    this.points = points;
    this.parentName = parentName;
  }
}
