package com.leadsphere.crm.patterns;

public class AddressFilter extends AbstractFilter {

  @Override
  public String execute(Order order) {
    var result = super.execute(order);
    if (order.getAddress() == null || order.getAddress().isEmpty()) {
      return result + "Invalid address! ";
    } else {
      return result;
    }
  }
}
