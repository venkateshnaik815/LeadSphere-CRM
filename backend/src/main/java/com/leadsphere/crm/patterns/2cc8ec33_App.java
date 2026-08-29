package com.leadsphere.crm.patterns;

import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;

@Slf4j
public class App {

  private static final String DB_URL = "jdbc:h2:~/testdb";

  private App() {}

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    final var dataSource = createDataSource();

    deleteSchema(dataSource);
    createSchema(dataSource);

    // Initializing Country Object China
    final var China = new Country(86, "China", "Asia", "Chinese");

    // Initializing Country Object UnitedArabEmirates
    final var UnitedArabEmirates = new Country(971, "United Arab Emirates", "Asia", "Arabic");

    // Initializing CountrySchemaSql Object with parameter "China" and "dataSource"
    final var serializedChina = new CountrySchemaSql(China, dataSource);
    // Initializing CountrySchemaSql Object with parameter "UnitedArabEmirates" and "dataSource"
    final var serializedUnitedArabEmirates = new CountrySchemaSql(UnitedArabEmirates, dataSource);

    serializedChina.insertCountry();
    serializedUnitedArabEmirates.insertCountry();

    serializedChina.selectCountry();
    serializedUnitedArabEmirates.selectCountry();
  }

  private static void deleteSchema(DataSource dataSource) {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(CountrySchemaSql.DELETE_SCHEMA_SQL);
    } catch (SQLException e) {
      LOGGER.info("Exception thrown " + e.getMessage());
    }
  }

  private static void createSchema(DataSource dataSource) {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(CountrySchemaSql.CREATE_SCHEMA_SQL);
    } catch (SQLException e) {
      LOGGER.info("Exception thrown " + e.getMessage());
    }
  }

  private static DataSource createDataSource() {
    var dataSource = new JdbcDataSource();
    dataSource.setURL(DB_URL);
    return dataSource;
  }
}
