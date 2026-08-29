package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Database;
import com.iluwatar.commander.Order;
import com.iluwatar.commander.exceptions.DatabaseUnavailableException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDatabase extends Database<Order> {
  private final Map<String, Order> data = new HashMap<>();

  @Override
  public Order add(Order o) throws DatabaseUnavailableException {
    return data.put(o.id, o);
  }

  @Override
  public Order get(String orderId) throws DatabaseUnavailableException {
    return data.get(orderId);
  }
}
