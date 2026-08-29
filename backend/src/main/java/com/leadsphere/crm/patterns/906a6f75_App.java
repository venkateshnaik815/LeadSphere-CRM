package com.leadsphere.crm.patterns;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    Function<Integer, Integer> timesTwo = x -> x * 2;
    Function<Integer, Integer> square = x -> x * x;

    Function<Integer, Integer> composedFunction =
        FunctionComposer.composeFunctions(timesTwo, square);

    int result = composedFunction.apply(3);
    LOGGER.info("Result of composing 'timesTwo' and 'square' functions applied to 3 is: " + result);
  }
}
