package com.leadsphere.crm.patterns;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(value = "FREIGHTER")
public class Freighter extends TransportVehicle {

  private double flightLength;

  public Freighter(String manufacturer, String model, int loadCapacity, double flightLength) {
    super(manufacturer, model, loadCapacity);
    this.flightLength = flightLength;
  }

  // Overridden the toString method to specify the Vehicle object
  @Override
  public String toString() {
    return "Freighter{ " + super.toString() + " ," + "flightLength=" + flightLength + '}';
  }
}
