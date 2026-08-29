package com.leadsphere.crm.patterns;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;

@Slf4j
public class App {
  private static final String DB_URL = "jdbc:h2:mem:dao;DB_CLOSE_DELAY=-1";
  private static final String ALL_CUSTOMERS = "customerDao.getAllCustomers(): ";

  public static void main(final String[] args) throws Exception {
    final var inMemoryDao = new InMemoryCustomerDao();
    performOperationsUsing(inMemoryDao);

    final var dataSource = createDataSource();
    createSchema(dataSource);
    final var dbDao = new DbCustomerDao(dataSource);
    performOperationsUsing(dbDao);
    deleteSchema(dataSource);
  }

  private static void deleteSchema(DataSource dataSource) throws SQLException {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(CustomerSchemaSql.DELETE_SCHEMA_SQL);
    }
  }

  private static void createSchema(DataSource dataSource) throws SQLException {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(CustomerSchemaSql.CREATE_SCHEMA_SQL);
    }
  }

  private static DataSource createDataSource() {
    var dataSource = new JdbcDataSource();
    dataSource.setURL(DB_URL);
    return dataSource;
  }

  private static void performOperationsUsing(final CustomerDao customerDao) throws Exception {
    addCustomers(customerDao);
    LOGGER.info(ALL_CUSTOMERS);
    try (var customerStream = customerDao.getAll()) {
      customerStream.forEach(customer -> LOGGER.info(customer.toString()));
    }
    LOGGER.info("customerDao.getCustomerById(2): " + customerDao.getById(2));
    final var customer = new Customer(4, "Dan", "Danson");
    customerDao.add(customer);
    LOGGER.info(ALL_CUSTOMERS + customerDao.getAll());
    customer.setFirstName("Daniel");
    customer.setLastName("Danielson");
    customerDao.update(customer);
    LOGGER.info(ALL_CUSTOMERS);
    try (var customerStream = customerDao.getAll()) {
      customerStream.forEach(cust -> LOGGER.info(cust.toString()));
    }
    customerDao.delete(customer);
    LOGGER.info(ALL_CUSTOMERS + customerDao.getAll());
  }

  private static void addCustomers(CustomerDao customerDao) throws Exception {
    for (var customer : generateSampleCustomers()) {
      customerDao.add(customer);
    }
  }

  public static List<Customer> generateSampleCustomers() {
    final var customer1 = new Customer(1, "Adam", "Adamson");
    final var customer2 = new Customer(2, "Bob", "Bobson");
    final var customer3 = new Customer(3, "Carl", "Carlson");
    return List.of(customer1, customer2, customer3);
  }
}
