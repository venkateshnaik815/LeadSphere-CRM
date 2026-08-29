
package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "updates", groupId = "subscriber-group")
  public void listenUpdates(String message) {
    log.info("[updates]: Received message: {}", message);
  }

  @KafkaListener(topics = "API", groupId = "subscriber-group")
  public void listenApi(String message) {
    log.info("[API]: Received message from /send : {}", message);
  }
}
