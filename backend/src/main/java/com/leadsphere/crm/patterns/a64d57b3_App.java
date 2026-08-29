package com.leadsphere.crm.patterns;

import com.google.inject.Guice;

public class App {

  public static void main(String[] args) {
    var simpleWizard = new SimpleWizard();
    simpleWizard.smoke();

    var advancedWizard = new AdvancedWizard(new SecondBreakfastTobacco());
    advancedWizard.smoke();

    var advancedSorceress = new AdvancedSorceress();
    advancedSorceress.setTobacco(new SecondBreakfastTobacco());
    advancedSorceress.smoke();

    var injector = Guice.createInjector(new TobaccoModule());
    var guiceWizard = injector.getInstance(GuiceWizard.class);
    guiceWizard.smoke();
  }
}
