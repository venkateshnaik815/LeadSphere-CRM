package com.leadsphere.crm.patterns;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class Publisher {

  private Publisher() {}

  public static Flux<Integer> publish(int start, int count, int delay) {
    return Flux.range(start, count).delayElements(Duration.ofMillis(delay)).log();
  }
}
