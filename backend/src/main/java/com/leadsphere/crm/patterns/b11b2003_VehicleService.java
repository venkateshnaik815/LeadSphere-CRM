package com.leadsphere.crm.patterns;

import com.iluwatar.entity.Vehicle;
import com.iluwatar.repository.VehicleRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;

  public Vehicle saveVehicle(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }

  public Vehicle getVehicle(int vehicleId) {
    return vehicleRepository.findById(vehicleId).orElse(null);
  }

  public List<Vehicle> getAllVehicles() {
    return vehicleRepository.findAll();
  }

  public Vehicle updateVehicle(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }

  public void deleteVehicle(Vehicle vehicle) {
    vehicleRepository.delete(vehicle);
  }
}
