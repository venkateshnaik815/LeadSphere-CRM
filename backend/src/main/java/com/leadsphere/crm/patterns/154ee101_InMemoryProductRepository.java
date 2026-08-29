package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {
  private final Map<String, Product> products = new HashMap<>();

  private static final double LAPTOP_PRICE = 1000.0;

  private static final double SMARTPHONE_PRICE = 500.0;

  public InMemoryProductRepository() {
    products.put("1", new Product("1", "Laptop", LAPTOP_PRICE));
    products.put("2", new Product("2", "Smartphone", SMARTPHONE_PRICE));
  }

  @Override
  public Product getProductById(final String productId) {
    return products.get(productId);
  }
}
