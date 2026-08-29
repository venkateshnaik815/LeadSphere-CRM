package com.leadsphere.crm.patterns;

import java.util.Arrays;
import java.util.Optional;

public abstract class Customer {

  public abstract boolean addRole(Role role);

  public abstract boolean hasRole(Role role);

  public abstract boolean remRole(Role role);

  public abstract <T extends Customer> Optional<T> getRole(Role role, Class<T> expectedRole);

  public static Customer newCustomer() {
    return new CustomerCore();
  }

  public static Customer newCustomer(Role... role) {
    var customer = newCustomer();
    Arrays.stream(role).forEach(customer::addRole);
    return customer;
  }
}
