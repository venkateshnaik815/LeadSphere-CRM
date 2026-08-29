package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    // create model, view and controller
    var giant = new GiantModel(Health.HEALTHY, Fatigue.ALERT, Nourishment.SATURATED);
    var view = new GiantView();
    var controller = new GiantController(giant, view);
    // initial display
    controller.updateView();
    // controller receives some interactions that affect the giant
    controller.setHealth(Health.WOUNDED);
    controller.setNourishment(Nourishment.HUNGRY);
    controller.setFatigue(Fatigue.TIRED);
    // redisplay
    controller.updateView();
  }
}
