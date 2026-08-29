package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.Order;
import java.util.List;

public interface OrderService {

  List<Order> getOrders(String userId);
}
