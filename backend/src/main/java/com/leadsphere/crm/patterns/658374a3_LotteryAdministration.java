package com.leadsphere.crm.patterns;

import static com.iluwatar.hexagonal.domain.LotteryConstants.PRIZE_AMOUNT;
import static com.iluwatar.hexagonal.domain.LotteryConstants.SERVICE_BANK_ACCOUNT;

import com.google.inject.Inject;
import com.iluwatar.hexagonal.banking.WireTransfers;
import com.iluwatar.hexagonal.database.LotteryTicketRepository;
import com.iluwatar.hexagonal.eventlog.LotteryEventLog;
import java.util.Map;

public class LotteryAdministration {

  private final LotteryTicketRepository repository;
  private final LotteryEventLog notifications;
  private final WireTransfers wireTransfers;

  @Inject
  public LotteryAdministration(
      LotteryTicketRepository repository,
      LotteryEventLog notifications,
      WireTransfers wireTransfers) {
    this.repository = repository;
    this.notifications = notifications;
    this.wireTransfers = wireTransfers;
  }

  public Map<LotteryTicketId, LotteryTicket> getAllSubmittedTickets() {
    return repository.findAll();
  }

  public LotteryNumbers performLottery() {
    var numbers = LotteryNumbers.createRandom();
    var tickets = getAllSubmittedTickets();
    for (var id : tickets.keySet()) {
      var lotteryTicket = tickets.get(id);
      var playerDetails = lotteryTicket.playerDetails();
      var playerAccount = playerDetails.bankAccount();
      var result = LotteryUtils.checkTicketForPrize(repository, id, numbers).getResult();
      if (result == LotteryTicketCheckResult.CheckResult.WIN_PRIZE) {
        if (wireTransfers.transferFunds(PRIZE_AMOUNT, SERVICE_BANK_ACCOUNT, playerAccount)) {
          notifications.ticketWon(playerDetails, PRIZE_AMOUNT);
        } else {
          notifications.prizeError(playerDetails, PRIZE_AMOUNT);
        }
      } else if (result == LotteryTicketCheckResult.CheckResult.NO_PRIZE) {
        notifications.ticketDidNotWin(playerDetails);
      }
    }
    return numbers;
  }

  public void resetLottery() {
    repository.deleteAll();
  }
}
