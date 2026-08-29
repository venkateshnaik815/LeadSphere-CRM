package com.leadsphere.crm.patterns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iluwatar.event.sourcing.state.AccountAggregate;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;

@Getter
public class MoneyTransferEvent extends DomainEvent {

  private final BigDecimal money;
  private final int accountNoFrom;
  private final int accountNoTo;

  @JsonCreator
  public MoneyTransferEvent(
      @JsonProperty("sequenceId") long sequenceId,
      @JsonProperty("createdTime") long createdTime,
      @JsonProperty("money") BigDecimal money,
      @JsonProperty("accountNoFrom") int accountNoFrom,
      @JsonProperty("accountNoTo") int accountNoTo) {
    super(sequenceId, createdTime, "MoneyTransferEvent");
    this.money = money;
    this.accountNoFrom = accountNoFrom;
    this.accountNoTo = accountNoTo;
  }

  @Override
  public void process() {
    var accountFrom =
        Optional.ofNullable(AccountAggregate.getAccount(accountNoFrom))
            .orElseThrow(() -> new RuntimeException("Account not found " + accountNoFrom));
    var accountTo =
        Optional.ofNullable(AccountAggregate.getAccount(accountNoTo))
            .orElseThrow(() -> new RuntimeException("Account not found " + accountNoTo));
    accountFrom.handleTransferFromEvent(this);
    accountTo.handleTransferToEvent(this);
  }
}
