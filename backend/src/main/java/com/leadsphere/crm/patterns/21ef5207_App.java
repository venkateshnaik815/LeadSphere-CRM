package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var cars = CarFactory.createCars();

    var modelsImperative = ImperativeProgramming.getModelsAfter2000(cars);
    LOGGER.info(modelsImperative.toString());

    var modelsFunctional = FunctionalProgramming.getModelsAfter2000(cars);
    LOGGER.info(modelsFunctional.toString());

    var groupingByCategoryImperative = ImperativeProgramming.getGroupingOfCarsByCategory(cars);
    LOGGER.info(groupingByCategoryImperative.toString());

    var groupingByCategoryFunctional = FunctionalProgramming.getGroupingOfCarsByCategory(cars);
    LOGGER.info(groupingByCategoryFunctional.toString());

    var john = new Person(cars);

    var sedansOwnedImperative = ImperativeProgramming.getSedanCarsOwnedSortedByDate(List.of(john));
    LOGGER.info(sedansOwnedImperative.toString());

    var sedansOwnedFunctional = FunctionalProgramming.getSedanCarsOwnedSortedByDate(List.of(john));
    LOGGER.info(sedansOwnedFunctional.toString());
  }
}
