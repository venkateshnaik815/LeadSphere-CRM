package com.leadsphere.crm.patterns;

import lombok.Getter;

public enum Action {
  HUNT("hunted a rabbit", "arrives for dinner"),
  TALE("tells a tale", "comes to listen"),
  GOLD("found gold", "takes his share of the gold"),
  ENEMY("spotted enemies", "runs for cover"),
  NONE("", "");

  private final String title;
  @Getter private final String description;

  Action(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String toString() {
    return title;
  }
}
