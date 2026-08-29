package com.leadsphere.crm.patterns;

import java.util.logging.Logger;

public class App {
  public static void main(String[] args) {

    final Logger logger = Logger.getLogger(App.class.getName());

    VehicleDatabase database = new VehicleDatabase();

    Car car = new Car(2020, "Toyota", "Corolla", 4, 1);
    Truck truck = new Truck(2018, "Ford", "F-150", 60, 2);

    database.saveVehicle(car);
    database.saveVehicle(truck);

    database.printAllVehicles();

    Vehicle vehicle = database.getVehicle(car.getId());
    Car retrievedCar = database.getCar(car.getId());
    Truck retrievedTruck = database.getTruck(truck.getId());

    logger.info(String.format("Retrieved Vehicle: %s", vehicle));
    logger.info(String.format("Retrieved Car: %s", retrievedCar));
    logger.info(String.format("Retrieved Truck: %s", retrievedTruck));
  }
}
