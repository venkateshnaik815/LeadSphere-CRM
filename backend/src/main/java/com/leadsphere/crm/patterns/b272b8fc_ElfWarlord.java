package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ElfWarlord extends Warlord {

  private final String helpType;

  public ElfWarlord(ElfWarlord elfWarlord) {
    super(elfWarlord);
    this.helpType = elfWarlord.helpType;
  }

  @Override
  public String toString() {
    return "Elven warlord helps in " + helpType;
  }
}
