package com.leadsphere.crm.patterns;

public class DAOFactoryProvider {

  private DAOFactoryProvider() {}

  public static DAOFactory getDataSource(DataSourceType dataSourceType) {
    return switch (dataSourceType) {
      case H2 -> new H2DataSourceFactory();
      case MONGO -> new MongoDataSourceFactory();
      case FLAT_FILE -> new FlatFileDataSourceFactory();
      default -> throw new IllegalArgumentException("Unsupported data source type");
    };
  }
}
