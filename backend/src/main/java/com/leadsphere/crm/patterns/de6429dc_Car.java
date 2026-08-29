package com.leadsphere.crm.patterns;

import lombok.Getter;

@Getter
public class Car extends Vehicle {
  private int numDoors;

  public Car(int year, String make, String model, int numDoors, int id) {
    super(year, make, model, id);
    if (numDoors <= 0) {
      throw new IllegalArgumentException("Number of doors must be positive.");
    }
    this.numDoors = numDoors;
  }

  public void setNumDoors(int doors) {
    if (doors <= 0) {
      throw new IllegalArgumentException("Number of doors must be positive.");
    }
    this.numDoors = doors;
  }

  @Override
  public String toString() {
    return "Car{"
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
        + ", numberOfDoors="
        + getNumDoors()
        + '}';
  }
}
