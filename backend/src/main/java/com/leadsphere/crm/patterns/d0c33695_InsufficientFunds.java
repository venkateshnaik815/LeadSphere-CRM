package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsufficientFunds implements ReceiptViewModel {

  private String userName;
  private Double amount;
  private String itemName;

  public InsufficientFunds(String userName, Double amount, String itemName) {
    this.userName = userName;
    this.amount = amount;
    this.itemName = itemName;
  }

  @Override
  public void show() {
    LOGGER.info(
        "Insufficient funds: "
            + amount
            + " of user: "
            + userName
            + " for buying item: "
            + itemName);
  }
}
