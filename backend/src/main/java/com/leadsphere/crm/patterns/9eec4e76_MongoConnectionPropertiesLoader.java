package com.leadsphere.crm.patterns;

import java.io.FileInputStream;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoConnectionPropertiesLoader {

  private static final String DEFAULT_HOST = "localhost";
  private static final int DEFAULT_PORT = 27017;

  public static void load() {
    var host = DEFAULT_HOST;
    var port = DEFAULT_PORT;
    var path = System.getProperty("hexagonal.properties.path");
    var properties = new Properties();
    if (path != null) {
      try (var fin = new FileInputStream(path)) {
        properties.load(fin);
        host = properties.getProperty("mongo-host");
        port = Integer.parseInt(properties.getProperty("mongo-port"));
      } catch (Exception e) {
        // error occurred, use default properties
        LOGGER.error("Error occurred: ", e);
      }
    }
    System.setProperty("mongo-host", host);
    System.setProperty("mongo-port", String.format("%d", port));
  }
}
