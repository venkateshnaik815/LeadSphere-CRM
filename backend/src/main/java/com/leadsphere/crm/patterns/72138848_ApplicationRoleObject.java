package com.leadsphere.crm.patterns;

import static com.iluwatar.roleobject.Role.BORROWER;
import static com.iluwatar.roleobject.Role.INVESTOR;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationRoleObject {

  public static void main(String[] args) {
    var customer = Customer.newCustomer(BORROWER, INVESTOR);

    LOGGER.info("New customer created : {}", customer);

    var hasBorrowerRole = customer.hasRole(BORROWER);
    LOGGER.info("Customer has a borrower role - {}", hasBorrowerRole);
    var hasInvestorRole = customer.hasRole(INVESTOR);
    LOGGER.info("Customer has an investor role - {}", hasInvestorRole);

    customer
        .getRole(INVESTOR, InvestorRole.class)
        .ifPresent(
            inv -> {
              inv.setAmountToInvest(1000);
              inv.setName("Billy");
            });
    customer.getRole(BORROWER, BorrowerRole.class).ifPresent(inv -> inv.setName("Johny"));

    customer
        .getRole(INVESTOR, InvestorRole.class)
        .map(InvestorRole::invest)
        .ifPresent(LOGGER::info);

    customer
        .getRole(BORROWER, BorrowerRole.class)
        .map(BorrowerRole::borrow)
        .ifPresent(LOGGER::info);
  }
}
