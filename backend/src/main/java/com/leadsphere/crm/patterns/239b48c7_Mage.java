package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class Mage extends Prototype<Mage> {

  public Mage(Mage source) {}
}
