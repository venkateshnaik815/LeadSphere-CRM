package com.leadsphere.crm.patterns;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;

@Slf4j
@Getter
@Setter
@Builder
public class Customer {

  @NonNull private final CustomerDao customerDao;
  @Builder.Default private List<Product> purchases = new ArrayList<>();
  @NonNull private String name;
  @NonNull private Money money;

  public void save() {
    try {
      Optional<Customer> customer = customerDao.findByName(name);
      if (customer.isPresent()) {
        customerDao.update(this);
      } else {
        customerDao.save(this);
      }
    } catch (SQLException ex) {
      LOGGER.error(ex.getMessage());
    }
  }

  public void buyProduct(Product product) {
    LOGGER.info(
        String.format(
            "%s want to buy %s($%.2f)...",
            name, product.getName(), product.getSalePrice().getAmount()));
    try {
      withdraw(product.getSalePrice());
    } catch (IllegalArgumentException ex) {
      LOGGER.error(ex.getMessage());
      return;
    }
    try {
      customerDao.addProduct(product, this);
      purchases.add(product);
      LOGGER.info(String.format("%s bought %s!", name, product.getName()));
    } catch (SQLException exception) {
      receiveMoney(product.getSalePrice());
      LOGGER.error(exception.getMessage());
    }
  }

  public void returnProduct(Product product) {
    LOGGER.info(
        String.format(
            "%s want to return %s($%.2f)...",
            name, product.getName(), product.getSalePrice().getAmount()));
    if (purchases.contains(product)) {
      try {
        customerDao.deleteProduct(product, this);
        purchases.remove(product);
        receiveMoney(product.getSalePrice());
        LOGGER.info(String.format("%s returned %s!", name, product.getName()));
      } catch (SQLException ex) {
        LOGGER.error(ex.getMessage());
      }
    } else {
      LOGGER.error(String.format("%s didn't buy %s...", name, product.getName()));
    }
  }

  public void showPurchases() {
    Optional<String> purchasesToShow =
        purchases.stream()
            .map(p -> p.getName() + " - $" + p.getSalePrice().getAmount())
            .reduce((p1, p2) -> p1 + ", " + p2);

    if (purchasesToShow.isPresent()) {
      LOGGER.info(name + " bought: " + purchasesToShow.get());
    } else {
      LOGGER.info(name + " didn't bought anything");
    }
  }

  public void showBalance() {
    LOGGER.info(name + " balance: " + money);
  }

  private void withdraw(Money amount) throws IllegalArgumentException {
    if (money.compareTo(amount) < 0) {
      throw new IllegalArgumentException("Not enough money!");
    }
    money = money.minus(amount);
  }

  private void receiveMoney(Money amount) {
    money = money.plus(amount);
  }
}
