package com.leadsphere.crm.patterns;

public record LotteryTicket(
    LotteryTicketId id, PlayerDetails playerDetails, LotteryNumbers lotteryNumbers) {

  @Override
  public int hashCode() {
    final var prime = 31;
    var result = 1;
    result = prime * result + ((lotteryNumbers == null) ? 0 : lotteryNumbers.hashCode());
    result = prime * result + ((playerDetails == null) ? 0 : playerDetails.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    var other = (LotteryTicket) obj;
    if (lotteryNumbers == null) {
      if (other.lotteryNumbers != null) {
        return false;
      }
    } else if (!lotteryNumbers.equals(other.lotteryNumbers)) {
      return false;
    }
    if (playerDetails == null) {
      return other.playerDetails == null;
    } else {
      return playerDetails.equals(other.playerDetails);
    }
  }
}
