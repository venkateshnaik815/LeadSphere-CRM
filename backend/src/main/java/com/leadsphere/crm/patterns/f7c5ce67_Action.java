package com.leadsphere.crm.patterns;

import com.iluwatar.model.view.controller.Fatigue;
import com.iluwatar.model.view.controller.Health;
import com.iluwatar.model.view.controller.Nourishment;

public class Action {

  public GiantModel giant;

  public Action(GiantModel giant) {
    this.giant = giant;
  }

  public void updateModel(Command command) {
    setFatigue(command.fatigue());
    setHealth(command.health());
    setNourishment(command.nourishment());
  }

  public void setHealth(Health health) {
    giant.setHealth(health);
  }

  public void setFatigue(Fatigue fatigue) {
    giant.setFatigue(fatigue);
  }

  public void setNourishment(Nourishment nourishment) {
    giant.setNourishment(nourishment);
  }
}
