package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    ParameterObject params =
        ParameterObject.newBuilder().withType("sneakers").sortBy("brand").build();
    LOGGER.info(params.toString());
    LOGGER.info(new SearchService().search(params));
  }
}
