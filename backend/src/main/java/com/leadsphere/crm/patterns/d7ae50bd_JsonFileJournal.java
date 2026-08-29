package com.leadsphere.crm.patterns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iluwatar.event.sourcing.event.AccountCreateEvent;
import com.iluwatar.event.sourcing.event.DomainEvent;
import com.iluwatar.event.sourcing.event.MoneyDepositEvent;
import com.iluwatar.event.sourcing.event.MoneyTransferEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonFileJournal extends EventJournal {

  private final List<String> events = new ArrayList<>();
  private int index = 0;

  public JsonFileJournal() {
    file = new File("Journal.json");
    if (file.exists()) {
      try (var input =
          new BufferedReader(
              new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
        String line;
        while ((line = input.readLine()) != null) {
          events.add(line);
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      reset();
    }
  }

  @Override
  public void write(DomainEvent domainEvent) {
    var mapper = new ObjectMapper();
    try (var output =
        new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
      var eventString = mapper.writeValueAsString(domainEvent);
      output.write(eventString + "\r\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public DomainEvent readNext() {
    if (index >= events.size()) {
      return null;
    }
    var event = events.get(index);
    index++;

    var mapper = new ObjectMapper();
    DomainEvent domainEvent;
    try {
      var jsonElement = mapper.readTree(event);
      var eventClassName = jsonElement.get("eventClassName").asText();
      domainEvent =
          switch (eventClassName) {
            case "AccountCreateEvent" -> mapper.treeToValue(jsonElement, AccountCreateEvent.class);
            case "MoneyDepositEvent" -> mapper.treeToValue(jsonElement, MoneyDepositEvent.class);
            case "MoneyTransferEvent" -> mapper.treeToValue(jsonElement, MoneyTransferEvent.class);
            default -> throw new RuntimeException("Journal Event not recognized");
          };
    } catch (JsonProcessingException jsonProcessingException) {
      throw new RuntimeException("Failed to convert JSON");
    }

    domainEvent.setRealTime(false);
    return domainEvent;
  }
}
