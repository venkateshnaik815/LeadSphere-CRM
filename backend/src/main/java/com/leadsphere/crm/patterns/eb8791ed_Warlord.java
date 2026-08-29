package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class Warlord extends Prototype<Warlord> {

  public Warlord(Warlord source) {}
}
