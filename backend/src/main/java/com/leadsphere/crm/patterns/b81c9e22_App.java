package com.leadsphere.crm.patterns;

import com.iluwatar.separatedinterface.invoice.InvoiceGenerator;
import com.iluwatar.separatedinterface.taxes.DomesticTaxCalculator;
import com.iluwatar.separatedinterface.taxes.ForeignTaxCalculator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static final double PRODUCT_COST = 50.0;

  public static void main(String[] args) {
    // Create the invoice generator with product cost as 50 and foreign product tax
    var internationalProductInvoice =
        new InvoiceGenerator(PRODUCT_COST, new ForeignTaxCalculator());
    LOGGER.info("Foreign Tax applied: {}", "" + internationalProductInvoice.getAmountWithTax());

    // Create the invoice generator with product cost as 50 and domestic product tax
    var domesticProductInvoice = new InvoiceGenerator(PRODUCT_COST, new DomesticTaxCalculator());
    LOGGER.info("Domestic Tax applied: {}", "" + domesticProductInvoice.getAmountWithTax());
  }
}
