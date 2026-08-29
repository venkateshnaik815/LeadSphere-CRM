package com.leadsphere.crm.patterns;

import com.iluwatar.model.view.controller.Fatigue;
import com.iluwatar.model.view.controller.Health;
import com.iluwatar.model.view.controller.Nourishment;
import lombok.Getter;

public class GiantModel {

  private final com.iluwatar.model.view.controller.GiantModel model;
  @Getter private final String name;

  GiantModel(String name, Health health, Fatigue fatigue, Nourishment nourishment) {
    this.name = name;
    this.model = new com.iluwatar.model.view.controller.GiantModel(health, fatigue, nourishment);
  }

  Health getHealth() {
    return model.getHealth();
  }

  void setHealth(Health health) {
    model.setHealth(health);
  }

  Fatigue getFatigue() {
    return model.getFatigue();
  }

  void setFatigue(Fatigue fatigue) {
    model.setFatigue(fatigue);
  }

  Nourishment getNourishment() {
    return model.getNourishment();
  }

  void setNourishment(Nourishment nourishment) {
    model.setNourishment(nourishment);
  }

  @Override
  public String toString() {
    return String.format(
        "Giant %s, The giant looks %s, %s and %s.",
        name, model.getHealth(), model.getFatigue(), model.getNourishment());
  }
}
