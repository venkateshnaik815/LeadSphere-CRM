package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController {

  @PostMapping("/payment/process")
  public ResponseEntity<Boolean> payment(@RequestBody(required = false) String request) {
    LOGGER.info("Received payment request: {}", request);
    boolean result = true;
    LOGGER.info("Payment result: {}", result);
    return ResponseEntity.ok(result);
  }
}
