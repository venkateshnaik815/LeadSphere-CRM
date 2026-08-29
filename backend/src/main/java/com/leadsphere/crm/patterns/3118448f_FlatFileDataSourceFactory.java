package com.leadsphere.crm.patterns;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FlatFileDataSourceFactory extends DAOFactory {
  private static final String FILE_PATH =
      System.getProperty("user.home") + "/Desktop/customer.json";

  @Override
  public CustomerDAO<Long> createCustomerDAO() {
    Path filePath = Paths.get(FILE_PATH);
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    return new FlatFileCustomerDAO(filePath, gson);
  }
}
