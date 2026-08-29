package com.leadsphere.crm.patterns;

import java.util.Map;

public class ProductFrontend extends FrontendComponent {

  @Override
  protected String getData(Map<String, String> params) {
    String category = params.getOrDefault("category", "all");
    return "Product List for category '" + category + "': [Product 1, Product 2, Product 3]";
  }
}
