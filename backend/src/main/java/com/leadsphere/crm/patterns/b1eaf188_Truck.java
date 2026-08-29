package com.leadsphere.crm.patterns;

import lombok.Getter;

@Getter
public class Truck extends Vehicle {
  private double loadCapacity;

  public Truck(int year, String make, String model, double loadCapacity, int id) {
    super(year, make, model, id);
    if (loadCapacity <= 0) {
      throw new IllegalArgumentException("Load capacity must be positive.");
    }
    this.loadCapacity = loadCapacity;
  }

  public void setLoadCapacity(double capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Load capacity must be positive.");
    }
    this.loadCapacity = capacity;
  }

  @Override
  public String toString() {
    return "Truck{"
        + "id="
        + getId()
        + ", make='"
        + getMake()
        + '\''
        + ", model='"
        + getModel()
        + '\''
        + ", year="
        + getYear()
        + ", payloadCapacity="
        + getLoadCapacity()
        + '}';
  }
}
