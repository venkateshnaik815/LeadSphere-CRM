package com.leadsphere.crm.patterns;

import com.iluwatar.entity.Car;
import com.iluwatar.entity.Truck;
import com.iluwatar.entity.Vehicle;
import com.iluwatar.service.VehicleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class SingleTableInheritance implements CommandLineRunner {

  // Autowiring the VehicleService class to execute the business logic methods
  private final VehicleService vehicleService;

  public static void main(String[] args) {
    var context = SpringApplication.run(SingleTableInheritance.class, args);
    if (args.length > 0 && "test".equals(args[0])) {
      // Close the context immediately during tests to prevent Tomcat/background threads from
      // hanging the JVM
      context.close();
    }
  }

  @Override
  public void run(String... args) {

    Logger log = LoggerFactory.getLogger(SingleTableInheritance.class);

    log.info("Saving Vehicles :- ");

    // Saving Car to DB as a Vehicle
    Vehicle vehicle1 = new Car("Tesla", "Model S", 4, 825);
    Vehicle car1 = vehicleService.saveVehicle(vehicle1);
    log.info("Vehicle 1 saved : {}", car1);

    // Saving Truck to DB as a Vehicle
    Vehicle vehicle2 = new Truck("Ford", "F-150", 3325, 14000);
    Vehicle truck1 = vehicleService.saveVehicle(vehicle2);
    log.info("Vehicle 2 saved : {}\n", truck1);

    log.info("Fetching Vehicles :- ");

    // Fetching the Car from DB
    Car savedCar1 = (Car) vehicleService.getVehicle(vehicle1.getVehicleId());
    log.info("Fetching Car1 from DB : {}", savedCar1);

    // Fetching the Truck from DB
    Truck savedTruck1 = (Truck) vehicleService.getVehicle(vehicle2.getVehicleId());
    log.info("Fetching Truck1 from DB : {}\n", savedTruck1);

    log.info("Fetching All Vehicles :- ");

    // Fetching the Vehicles present in the DB
    List<Vehicle> allVehiclesFromDb = vehicleService.getAllVehicles();
    allVehiclesFromDb.forEach(s -> log.info(s.toString()));
  }
}
