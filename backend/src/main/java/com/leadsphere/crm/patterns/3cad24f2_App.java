package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    // create the alchemist shop with the potions
    var alchemistShop = new AlchemistShop();
    // a brave visitor enters the alchemist shop and drinks all the potions
    alchemistShop.drinkPotions();
  }
}
