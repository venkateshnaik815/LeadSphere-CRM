package com.leadsphere.crm.patterns;

public record CartItem(Product product, int quantity) {

  public double lineTotal() {
    return quantity * product.priceUsd();
  }
}
