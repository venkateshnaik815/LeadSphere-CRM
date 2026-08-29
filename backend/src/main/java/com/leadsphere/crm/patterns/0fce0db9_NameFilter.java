package com.leadsphere.crm.patterns;

public class NameFilter extends AbstractFilter {

  @Override
  public String execute(Order order) {
    var result = super.execute(order);
    var name = order.getName();
    if (name == null || name.isEmpty() || name.matches(".*[^\\w|\\s]+.*")) {
      return result + "Invalid name! ";
    } else {
      return result;
    }
  }
}
