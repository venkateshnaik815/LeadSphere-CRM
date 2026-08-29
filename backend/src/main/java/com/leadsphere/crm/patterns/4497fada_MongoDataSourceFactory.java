package com.leadsphere.crm.patterns;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoDataSourceFactory extends DAOFactory {
  private static final String CONN_STR = "mongodb://localhost:27017/";
  private static final String DB_NAME = "dao_factory";
  private static final String COLLECTION_NAME = "customer";

  @Override
  public CustomerDAO<ObjectId> createCustomerDAO() {
    try {
      MongoClient mongoClient = MongoClients.create(CONN_STR);
      MongoDatabase database = mongoClient.getDatabase(DB_NAME);
      MongoCollection<Document> customerCollection = database.getCollection(COLLECTION_NAME);
      return new MongoCustomerDAO(customerCollection);
    } catch (CustomException e) {
      throw new CustomException("Error: " + e);
    }
  }
}
