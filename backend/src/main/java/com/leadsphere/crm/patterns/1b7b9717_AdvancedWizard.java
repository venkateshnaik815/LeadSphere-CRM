package com.leadsphere.crm.patterns;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdvancedWizard implements Wizard {

  private final Tobacco tobacco;

  @Override
  public void smoke() {
    tobacco.smoke(this);
  }
}
