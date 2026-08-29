package com.leadsphere.crm.patterns;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "TRUCK")
public class Truck extends TransportVehicle {

  private int towingCapacity;

  public Truck(String manufacturer, String model, int loadCapacity, int towingCapacity) {
    super(manufacturer, model, loadCapacity);
    this.towingCapacity = towingCapacity;
  }

  // Overridden the toString method to specify the Vehicle object
  @Override
  public String toString() {
    return "Truck{ " + super.toString() + ", " + "towingCapacity=" + towingCapacity + '}';
  }
}
