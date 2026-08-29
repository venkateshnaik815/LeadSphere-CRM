package com.leadsphere.crm.patterns;

import static org.joda.money.CurrencyUnit.USD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.joda.money.Money;

public class CustomerDaoImpl implements CustomerDao {

  private final DataSource dataSource;

  public CustomerDaoImpl(final DataSource userDataSource) {
    this.dataSource = userDataSource;
  }

  @Override
  public Optional<Customer> findByName(String name) throws SQLException {
    var sql = "select * from CUSTOMERS where name = ?;";

    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, name);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        return Optional.of(
            Customer.builder()
                .name(rs.getString("name"))
                .money(Money.of(USD, rs.getBigDecimal("money")))
                .customerDao(this)
                .build());
      } else {
        return Optional.empty();
      }
    }
  }

  @Override
  public void update(Customer customer) throws SQLException {
    var sql = "update CUSTOMERS set money = ? where name = ?;";
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setBigDecimal(1, customer.getMoney().getAmount());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.executeUpdate();
    }
  }

  @Override
  public void save(Customer customer) throws SQLException {
    var sql = "insert into CUSTOMERS (name, money) values (?, ?)";
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, customer.getName());
      preparedStatement.setBigDecimal(2, customer.getMoney().getAmount());
      preparedStatement.executeUpdate();
    }
  }

  @Override
  public void addProduct(Product product, Customer customer) throws SQLException {
    var sql = "insert into PURCHASES (product_name, customer_name) values (?,?)";
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, product.getName());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.executeUpdate();
    }
  }

  @Override
  public void deleteProduct(Product product, Customer customer) throws SQLException {
    var sql = "delete from PURCHASES where product_name = ? and customer_name = ?";
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, product.getName());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.executeUpdate();
    }
  }
}
