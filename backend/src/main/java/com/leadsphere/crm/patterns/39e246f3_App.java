package com.leadsphere.crm.patterns;

import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;

@Slf4j
public final class App {
  private static final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

  private App() {}

  public static void main(final String[] args) throws SQLException {
    // Create data source and create the user table.
    final var dataSource = createDataSource();
    createSchema(dataSource);
    var userTableModule = new UserTableModule(dataSource);

    // Initialize two users.
    var user1 = new User(1, "123456", "123456");
    var user2 = new User(2, "test", "password");

    // Login and register using the instance of userTableModule.
    userTableModule.registerUser(user1);
    userTableModule.login(user1.getUsername(), user1.getPassword());
    userTableModule.login(user2.getUsername(), user2.getPassword());
    userTableModule.registerUser(user2);
    userTableModule.login(user2.getUsername(), user2.getPassword());

    deleteSchema(dataSource);
  }

  private static void deleteSchema(final DataSource dataSource) throws SQLException {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(UserTableModule.DELETE_SCHEMA_SQL);
    }
  }

  private static void createSchema(final DataSource dataSource) throws SQLException {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(UserTableModule.CREATE_SCHEMA_SQL);
    }
  }

  private static DataSource createDataSource() {
    var dataSource = new JdbcDataSource();
    dataSource.setURL(DB_URL);
    return dataSource;
  }
}
