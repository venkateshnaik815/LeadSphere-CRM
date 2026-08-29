package com.leadsphere.crm.patterns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountrySchemaSql implements CountryDao {
  public static final String CREATE_SCHEMA_SQL =
      "CREATE TABLE IF NOT EXISTS WORLD (ID INT PRIMARY KEY, COUNTRY BLOB)";

  public static final String DELETE_SCHEMA_SQL = "DROP TABLE WORLD IF EXISTS";

  private Country country;
  private DataSource dataSource;

  public CountrySchemaSql(Country country, DataSource dataSource) {
    this.country =
        new Country(
            country.getCode(), country.getName(), country.getContinents(), country.getLanguage());
    this.dataSource = dataSource;
  }

  @Override
  public int insertCountry() throws IOException {
    var sql = "INSERT INTO WORLD (ID, COUNTRY) VALUES (?, ?)";
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oss = new ObjectOutputStream(baos)) {

      oss.writeObject(country);
      oss.flush();

      preparedStatement.setInt(1, country.getCode());
      preparedStatement.setBlob(2, new ByteArrayInputStream(baos.toByteArray()));
      preparedStatement.execute();
      return country.getCode();
    } catch (SQLException e) {
      LOGGER.info("Exception thrown " + e.getMessage());
    }
    return -1;
  }

  @Override
  public int selectCountry() throws IOException, ClassNotFoundException {
    var sql = "SELECT ID, COUNTRY FROM WORLD WHERE ID = ?";
    try (var connection = dataSource.getConnection();
        var preparedStatement = connection.prepareStatement(sql)) {

      preparedStatement.setInt(1, country.getCode());

      try (ResultSet rs = preparedStatement.executeQuery()) {
        if (rs.next()) {
          Blob countryBlob = rs.getBlob("country");
          ByteArrayInputStream baos =
              new ByteArrayInputStream(countryBlob.getBytes(1, (int) countryBlob.length()));
          ObjectInputStream ois = new ObjectInputStream(baos);
          country = (Country) ois.readObject();
          LOGGER.info("Country: " + country);
        }
        return rs.getInt("id");
      }
    } catch (SQLException e) {
      LOGGER.info("Exception thrown " + e.getMessage());
    }
    return -1;
  }
}
