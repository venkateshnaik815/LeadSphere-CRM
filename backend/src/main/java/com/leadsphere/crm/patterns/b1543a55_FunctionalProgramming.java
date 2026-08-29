
package com.leadsphere.crm.patterns;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FunctionalProgramming {
  private FunctionalProgramming() {}

  public static List<String> getModelsAfter2000(List<Car> cars) {
    return cars.stream()
        .filter(car -> car.year() > 2000)
        .sorted(Comparator.comparing(Car::year))
        .map(Car::model)
        .toList();
  }

  public static Map<Category, List<Car>> getGroupingOfCarsByCategory(List<Car> cars) {
    return cars.stream().collect(Collectors.groupingBy(Car::category));
  }

  public static List<Car> getSedanCarsOwnedSortedByDate(List<Person> persons) {
    return persons.stream()
        .map(Person::cars)
        .flatMap(List::stream)
        .filter(car -> Category.SEDAN.equals(car.category()))
        .sorted(Comparator.comparing(Car::year))
        .toList();
  }
}
