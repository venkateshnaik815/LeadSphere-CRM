package com.leadsphere.crm.patterns;

import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;

@Getter
public class Consumer {

  private final AtomicLong sumOfSquaredNumbers;

  Consumer(Long init) {
    sumOfSquaredNumbers = new AtomicLong(init);
  }

  public Long add(final Long num) {
    return sumOfSquaredNumbers.addAndGet(num);
  }
}
