package com.leadsphere.crm.patterns;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

public class H2DataSourceFactory extends DAOFactory {
  private static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
  private static final String USER = "sa";
  private static final String PASS = "";

  @Override
  public CustomerDAO createCustomerDAO() {
    return new H2CustomerDAO(createDataSource());
  }

  private DataSource createDataSource() {
    var dataSource = new JdbcDataSource();
    dataSource.setURL(DB_URL);
    dataSource.setUser(USER);
    dataSource.setPassword(PASS);
    return dataSource;
  }
}
