package com.leadsphere.crm.patterns;

import java.util.List;

public record CustomerResource(List<CustomerDto> customers) {
  public void save(CustomerDto customer) {
    customers.add(customer);
  }

  public void delete(String customerId) {
    customers.removeIf(customer -> customer.id().equals(customerId));
  }
}
