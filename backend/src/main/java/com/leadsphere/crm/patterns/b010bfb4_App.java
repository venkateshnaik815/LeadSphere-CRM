package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static void main(String[] args) {
    LOGGER.info("Creating pipeline");
    var filters =
        new Pipeline<>(new RemoveAlphabetsHandler())
            .addHandler(new RemoveDigitsHandler())
            .addHandler(new ConvertToCharArrayHandler());
    var input = "GoYankees123!";
    LOGGER.info("Executing pipeline with input: {}", input);
    var output = filters.execute(input);
    LOGGER.info("Pipeline output: {}", output);
  }
}
