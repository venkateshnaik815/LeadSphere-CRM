package com.leadsphere.crm.patterns;

import java.util.List;

public class CarFactory {
  private CarFactory() {}

  public static List<Car> createCars() {
    return List.of(
        new Car("Jeep", "Wrangler", 2011, Category.JEEP),
        new Car("Jeep", "Comanche", 1990, Category.JEEP),
        new Car("Dodge", "Avenger", 2010, Category.SEDAN),
        new Car("Buick", "Cascada", 2016, Category.CONVERTIBLE),
        new Car("Ford", "Focus", 2012, Category.SEDAN),
        new Car("Chevrolet", "Geo Metro", 1992, Category.CONVERTIBLE));
  }
}
