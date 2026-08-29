
package com.leadsphere.crm.patterns;

public record Product(int id, String name, double price, String description) {
  @Override
  public String toString() {
    return "ID: " + id + "\nName: " + name + "\nPrice: $" + price + "\nDescription: " + description;
  }
}
