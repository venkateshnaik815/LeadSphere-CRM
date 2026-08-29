package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutOfStock implements ReceiptViewModel {

  private static final Logger LOGGER = LoggerFactory.getLogger(OutOfStock.class);

  private final String userName;
  private final String itemName;

  public OutOfStock(String userName, String itemName) {
    this.userName = userName;
    this.itemName = itemName;
  }

  @Override
  public void show() {
    LOGGER.info(String.format("Out of stock: %s for user = %s to buy", itemName, userName));
  }
}
