package com.leadsphere.crm.patterns;

import com.iluwatar.hexagonal.domain.PlayerDetails;

public interface LotteryEventLog {

  void ticketSubmitted(PlayerDetails details);

  void ticketSubmitError(PlayerDetails details);

  void ticketDidNotWin(PlayerDetails details);

  void ticketWon(PlayerDetails details, int prizeAmount);

  void prizeError(PlayerDetails details, int prizeAmount);
}
