package com.leadsphere.crm.patterns;

import com.iluwatar.model.view.controller.Fatigue;
import com.iluwatar.model.view.controller.Health;
import com.iluwatar.model.view.controller.Nourishment;

public class App {

  public static void main(String[] args) {
    // create model, view and controller
    var giant1 = new GiantModel("giant1", Health.HEALTHY, Fatigue.ALERT, Nourishment.SATURATED);
    var giant2 = new GiantModel("giant2", Health.DEAD, Fatigue.SLEEPING, Nourishment.STARVING);
    var action1 = new Action(giant1);
    var action2 = new Action(giant2);
    var view = new GiantView();
    var dispatcher = new Dispatcher(view);
    dispatcher.addAction(action1);
    dispatcher.addAction(action2);
    var controller = new GiantController(dispatcher);

    // initial display
    controller.updateView(giant1);
    controller.updateView(giant2);

    // controller receives some interactions that affect the giant
    controller.setCommand(new Command(Fatigue.SLEEPING, Health.HEALTHY, Nourishment.STARVING), 0);
    controller.setCommand(new Command(Fatigue.ALERT, Health.HEALTHY, Nourishment.HUNGRY), 1);

    // redisplay
    controller.updateView(giant1);
    controller.updateView(giant2);
    // controller receives some interactions that affect the giant
  }
}
