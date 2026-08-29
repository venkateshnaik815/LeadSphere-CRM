package com.leadsphere.crm.patterns;

import com.iluwatar.hexagonal.database.LotteryTicketRepository;
import com.iluwatar.hexagonal.domain.LotteryTicketCheckResult.CheckResult;

public class LotteryUtils {

  private LotteryUtils() {}

  public static LotteryTicketCheckResult checkTicketForPrize(
      LotteryTicketRepository repository, LotteryTicketId id, LotteryNumbers winningNumbers) {
    var optional = repository.findById(id);
    if (optional.isPresent()) {
      if (optional.get().lotteryNumbers().equals(winningNumbers)) {
        return new LotteryTicketCheckResult(CheckResult.WIN_PRIZE, 1000);
      } else {
        return new LotteryTicketCheckResult(CheckResult.NO_PRIZE);
      }
    } else {
      return new LotteryTicketCheckResult(CheckResult.TICKET_NOT_SUBMITTED);
    }
  }
}
