package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var controller = new FrontController();
    controller.handleRequest("Archer");
    controller.handleRequest("Catapult");
    controller.handleRequest("foobar");
  }
}
