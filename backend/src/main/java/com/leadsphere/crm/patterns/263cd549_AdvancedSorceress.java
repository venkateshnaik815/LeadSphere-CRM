package com.leadsphere.crm.patterns;

import lombok.Setter;

@Setter
public class AdvancedSorceress implements Wizard {

  private Tobacco tobacco;

  @Override
  public void smoke() {
    tobacco.smoke(this);
  }
}
