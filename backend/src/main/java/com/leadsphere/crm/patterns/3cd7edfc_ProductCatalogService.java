
package com.leadsphere.crm.patterns;

import java.util.Map;

public class ProductCatalogService {

  private final Map<Integer, Product> products;

  public ProductCatalogService(Map<Integer, Product> products) {
    this.products = products;
  }

  // Additional methods to interact with products can be added here, for example:

  public Product getProductById(int id) {
    return products.get(id);
  }
}
