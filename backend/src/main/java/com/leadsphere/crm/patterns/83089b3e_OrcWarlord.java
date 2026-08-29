package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class OrcWarlord extends Warlord {

  private final String weapon;

  public OrcWarlord(OrcWarlord orcWarlord) {
    super(orcWarlord);
    this.weapon = orcWarlord.weapon;
  }

  @Override
  public String toString() {
    return "Orcish warlord attacks with " + weapon;
  }
}
