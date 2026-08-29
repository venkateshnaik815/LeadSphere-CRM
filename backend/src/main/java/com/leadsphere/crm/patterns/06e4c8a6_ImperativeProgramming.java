package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImperativeProgramming {
  private ImperativeProgramming() {}

  public static List<String> getModelsAfter2000(List<Car> cars) {
    List<Car> carsSortedByYear = new ArrayList<>();

    for (Car car : cars) {
      if (car.year() > 2000) {
        carsSortedByYear.add(car);
      }
    }

    Collections.sort(
        carsSortedByYear,
        new Comparator<Car>() {
          @Override
          public int compare(Car car1, Car car2) {
            return car1.year() - car2.year();
          }
        });

    List<String> models = new ArrayList<>();
    for (Car car : carsSortedByYear) {
      models.add(car.model());
    }

    return models;
  }

  public static Map<Category, List<Car>> getGroupingOfCarsByCategory(List<Car> cars) {
    Map<Category, List<Car>> groupingByCategory = new HashMap<>();
    for (Car car : cars) {
      if (groupingByCategory.containsKey(car.category())) {
        groupingByCategory.get(car.category()).add(car);
      } else {
        List<Car> categoryCars = new ArrayList<>();
        categoryCars.add(car);
        groupingByCategory.put(car.category(), categoryCars);
      }
    }
    return groupingByCategory;
  }

  public static List<Car> getSedanCarsOwnedSortedByDate(List<Person> persons) {
    List<Car> cars = new ArrayList<>();
    for (Person person : persons) {
      cars.addAll(person.cars());
    }

    List<Car> sedanCars = new ArrayList<>();
    for (Car car : cars) {
      if (Category.SEDAN.equals(car.category())) {
        sedanCars.add(car);
      }
    }

    sedanCars.sort(
        new Comparator<Car>() {
          @Override
          public int compare(Car o1, Car o2) {
            return o1.year() - o2.year();
          }
        });

    return sedanCars;
  }
}
