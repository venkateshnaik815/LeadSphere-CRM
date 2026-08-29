package com.leadsphere.crm.patterns;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserTableModule {
  public static final String CREATE_SCHEMA_SQL =
      "CREATE TABLE IF NOT EXISTS USERS (ID NUMBER, USERNAME VARCHAR(30) "
          + "UNIQUE,PASSWORD VARCHAR(30))";

  public static final String DELETE_SCHEMA_SQL = "DROP TABLE USERS IF EXISTS";

  private final DataSource dataSource;

  public UserTableModule(final DataSource userDataSource) {
    this.dataSource = userDataSource;
  }

  public int login(final String username, final String password) throws SQLException {
    var sql = "select count(*) from USERS where username=? and password=?";
    ResultSet resultSet = null;
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {
      var result = 0;
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        result = resultSet.getInt(1);
      }
      if (result == 1) {
        LOGGER.info("Login successfully!");
      } else {
        LOGGER.info("Fail to login!");
      }
      return result;
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
    }
  }

  public int registerUser(final User user) throws SQLException {
    var sql = "insert into USERS (username, password) values (?,?)";
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setString(1, user.getUsername());
      preparedStatement.setString(2, user.getPassword());
      var result = preparedStatement.executeUpdate();
      LOGGER.info("Register successfully!");
      return result;
    }
  }
}
