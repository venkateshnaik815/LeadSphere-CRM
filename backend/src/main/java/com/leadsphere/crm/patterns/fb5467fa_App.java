package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var facade = new DwarvenGoldmineFacade();
    facade.startNewDay();
    facade.digOutGold();
    facade.endDay();
  }
}
