package com.leadsphere.crm.patterns;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ConvertToCharArrayHandler implements Handler<String, char[]> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConvertToCharArrayHandler.class);

  @Override
  public char[] process(String input) {
    var characters = input.toCharArray();
    var string = Arrays.toString(characters);
    LOGGER.info(
        String.format(
            "Current handler: %s, input is %s of type %s, output is %s, of type %s",
            ConvertToCharArrayHandler.class, input, String.class, string, Character[].class));

    return characters;
  }
}
