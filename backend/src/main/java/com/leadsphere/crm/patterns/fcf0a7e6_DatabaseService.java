package com.leadsphere.crm.patterns;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;

@Slf4j
public class DatabaseService {

  public static final String CREATE_BINARY_SCHEMA_DDL =
      "CREATE TABLE IF NOT EXISTS FORESTS (ID NUMBER UNIQUE, NAME VARCHAR(30),FOREST VARBINARY)";
  public static final String CREATE_TEXT_SCHEMA_DDL =
      "CREATE TABLE IF NOT EXISTS FORESTS (ID NUMBER UNIQUE, NAME VARCHAR(30),FOREST VARCHAR)";
  public static final String DELETE_SCHEMA_SQL = "DROP TABLE FORESTS IF EXISTS";
  public static final String BINARY_DATA = "BINARY";
  private static final String DB_URL = "jdbc:h2:~/test";
  private static final String INSERT = "insert into FORESTS (id,name, forest) values (?,?,?)";
  private static final String SELECT = "select FOREST from FORESTS where id = ?";
  private static final DataSource dataSource = createDataSource();
  public String dataTypeDb;

  public DatabaseService(String dataTypeDb) {
    this.dataTypeDb = dataTypeDb;
  }

  private static DataSource createDataSource() {
    var dataSource = new JdbcDataSource();
    dataSource.setURL(DB_URL);
    return dataSource;
  }

  public void shutDownService() throws SQLException {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(DELETE_SCHEMA_SQL);
    }
  }

  public void startupService() throws SQLException {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      if (dataTypeDb.equals(BINARY_DATA)) {
        statement.execute(CREATE_BINARY_SCHEMA_DDL);
      } else {
        statement.execute(CREATE_TEXT_SCHEMA_DDL);
      }
    }
  }

  public void insert(int id, String name, Object data) throws SQLException {
    try (var connection = dataSource.getConnection();
        var insert = connection.prepareStatement(INSERT)) {
      insert.setInt(1, id);
      insert.setString(2, name);
      insert.setObject(3, data);
      insert.execute();
    }
  }

  public Object select(final long id, String columnsName) throws SQLException {
    ResultSet resultSet = null;
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(SELECT)) {
      Object result = null;
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        if (dataTypeDb.equals(BINARY_DATA)) {
          result = resultSet.getBinaryStream(columnsName);
        } else {
          result = resultSet.getString(columnsName);
        }
      }
      return result;
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
    }
  }
}
