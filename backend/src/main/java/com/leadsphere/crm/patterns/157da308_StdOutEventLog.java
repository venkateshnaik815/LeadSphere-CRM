package com.leadsphere.crm.patterns;

import com.iluwatar.hexagonal.domain.PlayerDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StdOutEventLog implements LotteryEventLog {

  @Override
  public void ticketSubmitted(PlayerDetails details) {
    LOGGER.info(
        "Lottery ticket for {} was submitted. Bank account {} was charged for 3 credits.",
        details.email(),
        details.bankAccount());
  }

  @Override
  public void ticketDidNotWin(PlayerDetails details) {
    LOGGER.info(
        "Lottery ticket for {} was checked and unfortunately did not win this time.",
        details.email());
  }

  @Override
  public void ticketWon(PlayerDetails details, int prizeAmount) {
    LOGGER.info(
        "Lottery ticket for {} has won! The bank account {} was deposited with {} credits.",
        details.email(),
        details.bankAccount(),
        prizeAmount);
  }

  @Override
  public void prizeError(PlayerDetails details, int prizeAmount) {
    LOGGER.error(
        "Lottery ticket for {} has won! Unfortunately the bank credit transfer of" + " {} failed.",
        details.email(),
        prizeAmount);
  }

  @Override
  public void ticketSubmitError(PlayerDetails details) {
    LOGGER.error(
        "Lottery ticket for {} could not be submitted because the credit transfer"
            + " of 3 credits failed.",
        details.email());
  }
}
