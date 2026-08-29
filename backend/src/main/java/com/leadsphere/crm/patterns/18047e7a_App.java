package com.leadsphere.crm.patterns;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static final String PROP_FILE_NAME = "config.properties";

  boolean interactiveMode = false;

  public static void main(String[] args) {
    var app = new App();
    app.setUp();
    app.run();
  }

  public void setUp() {
    var prop = new Properties();

    var inputStream = App.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);

    if (inputStream != null) {
      try {
        prop.load(inputStream);
      } catch (IOException e) {
        LOGGER.error("{} was not found. Defaulting to non-interactive mode.", PROP_FILE_NAME, e);
      }
      var property = prop.getProperty("INTERACTIVE_MODE");
      if (property.equalsIgnoreCase("YES")) {
        interactiveMode = true;
      }
    }
  }

  public void run() {
    if (interactiveMode) {
      runInteractiveMode();
    } else {
      quickRun();
    }
  }

  public void quickRun() {
    var eventManager = new EventManager();

    try {
      // Create an Asynchronous event.
      var asyncEventId = eventManager.createAsync(Duration.ofSeconds(60));
      LOGGER.info("Async Event [{}] has been created.", asyncEventId);
      eventManager.start(asyncEventId);
      LOGGER.info("Async Event [{}] has been started.", asyncEventId);

      // Create a Synchronous event.
      var syncEventId = eventManager.create(Duration.ofSeconds(60));
      LOGGER.info("Sync Event [{}] has been created.", syncEventId);
      eventManager.start(syncEventId);
      LOGGER.info("Sync Event [{}] has been started.", syncEventId);

      eventManager.status(asyncEventId);
      eventManager.status(syncEventId);

      eventManager.cancel(asyncEventId);
      LOGGER.info("Async Event [{}] has been stopped.", asyncEventId);
      eventManager.cancel(syncEventId);
      LOGGER.info("Sync Event [{}] has been stopped.", syncEventId);

    } catch (MaxNumOfEventsAllowedException
        | LongRunningEventException
        | EventDoesNotExistException
        | InvalidOperationException e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void runInteractiveMode() {
    var eventManager = new EventManager();

    var s = new Scanner(System.in);
    var option = -1;
    while (option != 4) {
      LOGGER.info("Hello. Would you like to boil some eggs?");
      LOGGER.info(
          """
              (1) BOIL AN EGG
              (2) STOP BOILING THIS EGG
              (3) HOW ARE MY EGGS?
              (4) EXIT
              """);
      LOGGER.info("Choose [1,2,3,4]: ");
      option = s.nextInt();

      if (option == 1) {
        processOption1(eventManager, s);
      } else if (option == 2) {
        processOption2(eventManager, s);
      } else if (option == 3) {
        processOption3(eventManager, s);
      } else if (option == 4) {
        eventManager.shutdown();
      }
    }

    s.close();
  }

  private void processOption3(EventManager eventManager, Scanner s) {
    s.nextLine();
    LOGGER.info("Just one egg (O) OR all of them (A) ?: ");
    var eggChoice = s.nextLine();

    if (eggChoice.equalsIgnoreCase("O")) {
      LOGGER.info("Which egg?: ");
      int eventId = s.nextInt();
      try {
        eventManager.status(eventId);
      } catch (EventDoesNotExistException e) {
        LOGGER.error(e.getMessage());
      }
    } else if (eggChoice.equalsIgnoreCase("A")) {
      eventManager.statusOfAllEvents();
    }
  }

  private void processOption2(EventManager eventManager, Scanner s) {
    LOGGER.info("Which egg?: ");
    var eventId = s.nextInt();
    try {
      eventManager.cancel(eventId);
      LOGGER.info("Egg [{}] is removed from boiler.", eventId);
    } catch (EventDoesNotExistException e) {
      LOGGER.error(e.getMessage());
    }
  }

  private void processOption1(EventManager eventManager, Scanner s) {
    s.nextLine();
    LOGGER.info("Boil multiple eggs at once (A) or boil them one-by-one (S)?: ");
    var eventType = s.nextLine();
    LOGGER.info("How long should this egg be boiled for (in seconds)?: ");
    var eventTime = Duration.ofSeconds(s.nextInt());
    if (eventType.equalsIgnoreCase("A")) {
      try {
        var eventId = eventManager.createAsync(eventTime);
        eventManager.start(eventId);
        LOGGER.info("Egg [{}] is being boiled.", eventId);
      } catch (MaxNumOfEventsAllowedException
          | LongRunningEventException
          | EventDoesNotExistException e) {
        LOGGER.error(e.getMessage());
      }
    } else if (eventType.equalsIgnoreCase("S")) {
      try {
        var eventId = eventManager.create(eventTime);
        eventManager.start(eventId);
        LOGGER.info("Egg [{}] is being boiled.", eventId);
      } catch (MaxNumOfEventsAllowedException
          | InvalidOperationException
          | LongRunningEventException
          | EventDoesNotExistException e) {
        LOGGER.error(e.getMessage());
      }
    } else {
      LOGGER.info("Unknown event type.");
    }
  }
}
