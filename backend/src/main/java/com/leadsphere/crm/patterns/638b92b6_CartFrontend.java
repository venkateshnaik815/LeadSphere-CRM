package com.leadsphere.crm.patterns;

import java.util.Map;

public class CartFrontend extends FrontendComponent {

  @Override
  protected String getData(Map<String, String> params) {
    String userId = params.getOrDefault("userId", "anonymous");
    return "Shopping Cart for user '" + userId + "': [Item 1, Item 2]";
  }
}
