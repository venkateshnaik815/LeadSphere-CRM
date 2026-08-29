package com.leadsphere.crm.patterns;

import com.iluwatar.specification.creature.Creature;
import com.iluwatar.specification.property.Size;

public class SizeSelector extends AbstractSelector<Creature> {

  private final Size size;

  public SizeSelector(Size s) {
    this.size = s;
  }

  @Override
  public boolean test(Creature t) {
    return t.getSize().equals(size);
  }
}
