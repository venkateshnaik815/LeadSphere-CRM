package com.leadsphere.crm.patterns;

public class ContactFilter extends AbstractFilter {

  @Override
  public String execute(Order order) {
    var result = super.execute(order);
    var contactNumber = order.getContactNumber();
    if (contactNumber == null
        || contactNumber.matches(".*[^\\d]+.*")
        || contactNumber.length() != 11) {
      return result + "Invalid contact number! ";
    } else {
      return result;
    }
  }
}
