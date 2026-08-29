package com.leadsphere.crm.patterns;

import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Mass;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.property.Size;

public abstract class AbstractCreature implements Creature {

  private final String name;
  private final Size size;
  private final Movement movement;
  private final Color color;
  private final Mass mass;

  public AbstractCreature(String name, Size size, Movement movement, Color color, Mass mass) {
    this.name = name;
    this.size = size;
    this.movement = movement;
    this.color = color;
    this.mass = mass;
  }

  @Override
  public String toString() {
    return String.format(
        "%s [size=%s, movement=%s, color=%s, mass=%s]", name, size, movement, color, mass);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Size getSize() {
    return size;
  }

  @Override
  public Movement getMovement() {
    return movement;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Mass getMass() {
    return mass;
  }
}
