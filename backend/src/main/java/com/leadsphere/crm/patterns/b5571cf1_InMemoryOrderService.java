package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.Order;
import com.iluwatar.bff.service.OrderService;
import java.util.List;
import java.util.Map;

public final class InMemoryOrderService implements OrderService {

  private final Map<String, List<Order>> ordersByUserId;

  public InMemoryOrderService(final Map<String, List<Order>> orders) {
    this.ordersByUserId = orders;
  }

  @Override
  public List<Order> getOrders(final String userId) {
    return ordersByUserId.getOrDefault(userId, List.of());
  }
}
