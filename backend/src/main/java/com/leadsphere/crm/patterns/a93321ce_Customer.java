package com.leadsphere.crm.patterns;

public record Customer(String id, String name) {

  @Override
  public String toString() {
    return "Customer{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
  }
}
