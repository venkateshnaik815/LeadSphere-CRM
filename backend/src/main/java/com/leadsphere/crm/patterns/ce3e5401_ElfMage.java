package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ElfMage extends Mage {

  private final String helpType;

  public ElfMage(ElfMage elfMage) {
    super(elfMage);
    this.helpType = elfMage.helpType;
  }

  @Override
  public String toString() {
    return "Elven mage helps in " + helpType;
  }
}
