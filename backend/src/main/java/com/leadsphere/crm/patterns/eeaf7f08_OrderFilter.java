package com.leadsphere.crm.patterns;

public class OrderFilter extends AbstractFilter {

  @Override
  public String execute(Order order) {
    var result = super.execute(order);
    var orderItem = order.getOrderItem();
    if (orderItem == null || orderItem.isEmpty()) {
      return result + "Invalid order! ";
    } else {
      return result;
    }
  }
}
