package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderRepository implements OrderRepository {
  private final List<Order> orders = new ArrayList<>();

  @Override
  public void saveOrder(final Order order) {
    orders.add(order);
  }
}
