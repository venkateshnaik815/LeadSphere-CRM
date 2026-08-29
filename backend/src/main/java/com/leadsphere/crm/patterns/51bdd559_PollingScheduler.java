
package com.leadsphere.crm.patterns;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PollingScheduler {

  private static final Logger log = LoggerFactory.getLogger(PollingScheduler.class);
  @Autowired private DataSourceService dataSourceService;
  @Autowired private KafkaProducer kafkaProducer;

  @Scheduled(fixedRate = 5000) // Poll every 5 seconds
  public void pollDataSource() {
    try {
      int id = new Random().nextInt(100); // Pick a random ID
      String data = dataSourceService.getData(id); // Get data from service

      if (data != null) {
        log.info("🟢 Publishing Data: {}", data);
        kafkaProducer.sendMessage("updates", data);
      } else {
        log.info("🔴 No Data Found for ID: {}", id);
      }
    } catch (Exception e) {
      log.error("Error while publishing data {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
