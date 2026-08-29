
package com.leadsphere.crm.patterns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollingController {

  @Autowired private KafkaProducer kafkaProducer;

  @GetMapping("/health")
  public String healthCheck() {
    return "Polling Service is up and running!";
  }

  @PostMapping("/send")
  public String sendMessage(@RequestParam("message") String message) {
    kafkaProducer.sendMessage("API", message);
    return "Message sent: " + message;
  }
}
