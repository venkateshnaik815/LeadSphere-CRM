package com.leadsphere.crm.patterns;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class LotteryTicketId {

  private static final AtomicInteger numAllocated = new AtomicInteger(0);
  private final int id;

  public LotteryTicketId() {
    this.id = numAllocated.incrementAndGet();
  }

  @Override
  public String toString() {
    return String.format("%d", id);
  }
}
