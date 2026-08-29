package com.leadsphere.crm.patterns;

public class SimpleWizard implements Wizard {

  private final OldTobyTobacco tobacco = new OldTobyTobacco();

  public void smoke() {
    tobacco.smoke(this);
  }
}
