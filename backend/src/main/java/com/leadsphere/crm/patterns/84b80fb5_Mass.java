package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Mass {

  private final double value;
  private final String title;

  public Mass(double value) {
    this.value = value;
    this.title = value + "kg"; // Implicit call to Double.toString(value)
  }

  public final boolean greaterThan(Mass other) {
    return this.value > other.value;
  }

  public final boolean smallerThan(Mass other) {
    return this.value < other.value;
  }

  public final boolean greaterThanOrEq(Mass other) {
    return this.value >= other.value;
  }

  public final boolean smallerThanOrEq(Mass other) {
    return this.value <= other.value;
  }

  @Override
  public String toString() {
    return title;
  }
}
