package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class OrcMage extends Mage {

  private final String weapon;

  public OrcMage(OrcMage orcMage) {
    super(orcMage);
    this.weapon = orcMage.weapon;
  }

  @Override
  public String toString() {
    return "Orcish mage attacks with " + weapon;
  }
}
