package com.leadsphere.crm.patterns;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

public final class CustomerRegistry {

  @Getter private static final CustomerRegistry instance = new CustomerRegistry();

  private final Map<String, Customer> customerMap;

  private CustomerRegistry() {
    customerMap = new ConcurrentHashMap<>();
  }

  public Customer addCustomer(Customer customer) {
    return customerMap.put(customer.id(), customer);
  }

  public Customer getCustomer(String id) {
    return customerMap.get(id);
  }
}
