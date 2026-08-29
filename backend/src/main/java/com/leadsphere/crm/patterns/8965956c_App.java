package com.leadsphere.crm.patterns;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class App {
  public static void main(String[] args) {
    var context = SpringApplication.run(App.class, args);
    if (args.length > 0 && "test".equals(args[0])) {
      // Close the context immediately during tests to prevent Tomcat/background threads from
      // hanging the JVM
      context.close();
    }
  }

  @Bean
  public CommandLineRunner run(RequestService requestService, RequestRepository requestRepository) {
    return args -> {
      Request req = requestService.create(UUID.randomUUID());
      requestService.create(req.getUuid());
      requestService.create(req.getUuid());
      LOGGER.info(
          "Nb of requests : {}", requestRepository.count()); // 1, processRequest is idempotent
      req = requestService.start(req.getUuid());
      try {
        req = requestService.start(req.getUuid());
      } catch (InvalidNextStateException ex) {
        LOGGER.error("Cannot start request twice!");
      }
      req = requestService.complete(req.getUuid());
      LOGGER.info("Request: {}", req);
    };
  }
}
