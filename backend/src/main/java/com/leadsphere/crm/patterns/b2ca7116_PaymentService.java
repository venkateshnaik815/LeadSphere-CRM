
package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {
  public static Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

  public void selectPaymentMethod(String method) {
    if (method.equals("cash")) {
      cashPayment();
    } else if (method.equals("credit")) {
      creditCardPayment();
    } else {
      LOGGER.info("Unspecified payment method type");
    }
  }

  public void cashPayment() {
    LOGGER.info("Client have chosen cash payment option");
  }

  public void creditCardPayment() {
    LOGGER.info("Client have chosen credit card payment option");
  }
}
