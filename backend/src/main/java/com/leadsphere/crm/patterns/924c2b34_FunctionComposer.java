package com.leadsphere.crm.patterns;

import java.util.function.Function;

public class FunctionComposer {

  private FunctionComposer() {}

  public static Function<Integer, Integer> composeFunctions(
      Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
    return f1.andThen(f2);
  }
}
