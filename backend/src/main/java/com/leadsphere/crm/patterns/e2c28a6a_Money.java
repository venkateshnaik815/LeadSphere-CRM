package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Money {
  private double amount;
  private String currency;

  private double roundToTwoDecimals(double value) {
    return Math.round(value * 100.0) / 100.0;
  }

  public void addMoney(Money moneyToBeAdded) throws CannotAddTwoCurrienciesException {
    if (!moneyToBeAdded.getCurrency().equals(this.currency)) {
      throw new CannotAddTwoCurrienciesException("You are trying to add two different currencies");
    }
    this.amount = roundToTwoDecimals(this.amount + moneyToBeAdded.getAmount());
  }

  public void subtractMoney(Money moneyToBeSubtracted) throws CannotSubtractException {
    if (!moneyToBeSubtracted.getCurrency().equals(this.currency)) {
      throw new CannotSubtractException("You are trying to subtract two different currencies");
    } else if (moneyToBeSubtracted.getAmount() > this.amount) {
      throw new CannotSubtractException(
          "The amount you are trying to subtract is larger than the amount you have");
    }
    this.amount = roundToTwoDecimals(this.amount - moneyToBeSubtracted.getAmount());
  }

  public void multiply(int factor) {
    if (factor < 0) {
      throw new IllegalArgumentException("Factor must be non-negative");
    }
    this.amount = roundToTwoDecimals(this.amount * factor);
  }

  public void exchangeCurrency(String currencyToChangeTo, double exchangeRate) {
    if (exchangeRate < 0) {
      throw new IllegalArgumentException("Exchange rate must be non-negative");
    }
    this.amount = roundToTwoDecimals(this.amount * exchangeRate);
    this.currency = currencyToChangeTo;
  }
}
