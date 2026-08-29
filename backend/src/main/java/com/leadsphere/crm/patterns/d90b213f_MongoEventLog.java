package com.leadsphere.crm.patterns;

import com.iluwatar.hexagonal.domain.PlayerDetails;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

public class MongoEventLog implements LotteryEventLog {

  private static final String DEFAULT_DB = "lotteryDB";
  private static final String DEFAULT_EVENTS_COLLECTION = "events";
  private static final String EMAIL = "email";
  private static final String PHONE = "phone";
  public static final String MESSAGE = "message";

  @Getter private MongoClient mongoClient;
  @Getter private MongoDatabase database;
  @Getter private MongoCollection<Document> eventsCollection;

  private final StdOutEventLog stdOutEventLog = new StdOutEventLog();

  public MongoEventLog() {
    connect();
  }

  public MongoEventLog(String dbName, String eventsCollectionName) {
    connect(dbName, eventsCollectionName);
  }

  public void connect() {
    connect(DEFAULT_DB, DEFAULT_EVENTS_COLLECTION);
  }

  public void connect(String dbName, String eventsCollectionName) {
    if (mongoClient != null) {
      mongoClient.close();
    }
    mongoClient =
        new MongoClient(
            System.getProperty("mongo-host"), Integer.parseInt(System.getProperty("mongo-port")));
    database = mongoClient.getDatabase(dbName);
    eventsCollection = database.getCollection(eventsCollectionName);
  }

  @Override
  public void ticketSubmitted(PlayerDetails details) {
    var document = new Document(EMAIL, details.email());
    document.put(PHONE, details.phoneNumber());
    document.put("bank", details.bankAccount());
    document.put(
        MESSAGE, "Lottery ticket was submitted and bank account was charged for 3 credits.");
    eventsCollection.insertOne(document);
    stdOutEventLog.ticketSubmitted(details);
  }

  @Override
  public void ticketSubmitError(PlayerDetails details) {
    var document = new Document(EMAIL, details.email());
    document.put(PHONE, details.phoneNumber());
    document.put("bank", details.bankAccount());
    document.put(MESSAGE, "Lottery ticket could not be submitted because lack of funds.");
    eventsCollection.insertOne(document);
    stdOutEventLog.ticketSubmitError(details);
  }

  @Override
  public void ticketDidNotWin(PlayerDetails details) {
    var document = new Document(EMAIL, details.email());
    document.put(PHONE, details.phoneNumber());
    document.put("bank", details.bankAccount());
    document.put(MESSAGE, "Lottery ticket was checked and unfortunately did not win this time.");
    eventsCollection.insertOne(document);
    stdOutEventLog.ticketDidNotWin(details);
  }

  @Override
  public void ticketWon(PlayerDetails details, int prizeAmount) {
    var document = new Document(EMAIL, details.email());
    document.put(PHONE, details.phoneNumber());
    document.put("bank", details.bankAccount());
    document.put(
        MESSAGE,
        String.format(
            "Lottery ticket won! The bank account was deposited with %d credits.", prizeAmount));
    eventsCollection.insertOne(document);
    stdOutEventLog.ticketWon(details, prizeAmount);
  }

  @Override
  public void prizeError(PlayerDetails details, int prizeAmount) {
    var document = new Document(EMAIL, details.email());
    document.put(PHONE, details.phoneNumber());
    document.put("bank", details.bankAccount());
    document.put(
        MESSAGE,
        String.format(
            "Lottery ticket won! Unfortunately the bank credit transfer of %d failed.",
            prizeAmount));
    eventsCollection.insertOne(document);
    stdOutEventLog.prizeError(details, prizeAmount);
  }
}
