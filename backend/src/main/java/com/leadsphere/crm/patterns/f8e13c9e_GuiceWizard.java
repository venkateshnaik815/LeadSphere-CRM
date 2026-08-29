package com.leadsphere.crm.patterns;

import javax.inject.Inject;

public class GuiceWizard implements Wizard {

  private final Tobacco tobacco;

  @Inject
  public GuiceWizard(Tobacco tobacco) {
    this.tobacco = tobacco;
  }

  @Override
  public void smoke() {
    tobacco.smoke(this);
  }
}
