package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class LotteryTicketCheckResult {

  public enum CheckResult {
    WIN_PRIZE,
    NO_PRIZE,
    TICKET_NOT_SUBMITTED
  }

  private final CheckResult result;
  private final int prizeAmount;

  public LotteryTicketCheckResult(CheckResult result) {
    this.result = result;
    prizeAmount = 0;
  }
}
