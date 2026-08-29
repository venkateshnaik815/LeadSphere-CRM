package com.leadsphere.crm.patterns;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import java.util.ArrayList;
import lombok.Getter;
import org.bson.Document;

public class MongoBank implements WireTransfers {

  private static final String DEFAULT_DB = "lotteryDB";
  private static final String DEFAULT_ACCOUNTS_COLLECTION = "accounts";

  @Getter private MongoClient mongoClient;
  @Getter private MongoDatabase database;
  @Getter private MongoCollection<Document> accountsCollection;

  public MongoBank() {
    connect();
  }

  public MongoBank(String dbName, String accountsCollectionName) {
    connect(dbName, accountsCollectionName);
  }

  public void connect() {
    connect(DEFAULT_DB, DEFAULT_ACCOUNTS_COLLECTION);
  }

  public void connect(String dbName, String accountsCollectionName) {
    if (mongoClient != null) {
      mongoClient.close();
    }
    mongoClient =
        new MongoClient(
            System.getProperty("mongo-host"), Integer.parseInt(System.getProperty("mongo-port")));
    database = mongoClient.getDatabase(dbName);
    accountsCollection = database.getCollection(accountsCollectionName);
  }

  @Override
  public void setFunds(String bankAccount, int amount) {
    var search = new Document("_id", bankAccount);
    var update = new Document("_id", bankAccount).append("funds", amount);
    var updateOptions = new UpdateOptions().upsert(true);
    accountsCollection.updateOne(search, new Document("$set", update), updateOptions);
  }

  @Override
  public int getFunds(String bankAccount) {
    return accountsCollection
        .find(new Document("_id", bankAccount))
        .limit(1)
        .into(new ArrayList<>())
        .stream()
        .findFirst()
        .map(x -> x.getInteger("funds"))
        .orElse(0);
  }

  @Override
  public boolean transferFunds(int amount, String sourceAccount, String destinationAccount) {
    var sourceFunds = getFunds(sourceAccount);
    if (sourceFunds < amount) {
      return false;
    } else {
      var destFunds = getFunds(destinationAccount);
      setFunds(sourceAccount, sourceFunds - amount);
      setFunds(destinationAccount, destFunds + amount);
      return true;
    }
  }
}
