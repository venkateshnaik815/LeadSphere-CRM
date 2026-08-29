package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vehicle {

  private String make;
  private String model;
  private int year;
  private int id;

  public Vehicle(int year, String make, String model, int id) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.id = id;
  }

  @Override
  public String toString() {
    return "Vehicle{"
        + "id="
        + id
        + ", make='"
        + make
        + '\''
        + ", model='"
        + model
        + '\''
        + ", year="
        + year
        + '}';
  }
}
