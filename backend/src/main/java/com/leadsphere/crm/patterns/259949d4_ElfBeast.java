package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ElfBeast extends Beast {

  private final String helpType;

  public ElfBeast(ElfBeast elfBeast) {
    super(elfBeast);
    this.helpType = elfBeast.helpType;
  }

  @Override
  public String toString() {
    return "Elven eagle helps in " + helpType;
  }
}
