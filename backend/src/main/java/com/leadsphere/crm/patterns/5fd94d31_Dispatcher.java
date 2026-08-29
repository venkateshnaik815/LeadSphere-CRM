package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Dispatcher {

  @Getter private final GiantView giantView;
  private final List<Action> actions;

  public Dispatcher(GiantView giantView) {
    this.giantView = giantView;
    this.actions = new ArrayList<>();
  }

  void addAction(Action action) {
    actions.add(action);
  }

  public void performAction(Command s, int actionIndex) {
    actions.get(actionIndex).updateModel(s);
  }

  public void updateView(GiantModel giantModel) {
    giantView.displayGiant(giantModel);
  }
}
