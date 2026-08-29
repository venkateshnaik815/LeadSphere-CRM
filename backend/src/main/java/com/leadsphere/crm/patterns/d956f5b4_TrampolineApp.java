package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrampolineApp {

  public static void main(String[] args) {
    LOGGER.info("Start calculating war casualties");
    var result = loop(10, 1).result();
    LOGGER.info("The number of orcs perished in the war: {}", result);
  }

  public static Trampoline<Integer> loop(int times, int prod) {
    if (times == 0) {
      return Trampoline.done(prod);
    } else {
      return Trampoline.more(() -> loop(times - 1, prod * times));
    }
  }
}
