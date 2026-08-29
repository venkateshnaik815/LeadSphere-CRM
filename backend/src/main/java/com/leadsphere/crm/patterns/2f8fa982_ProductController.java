package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProductController {

  @PostMapping("/product/validate")
  public ResponseEntity<Boolean> validateProduct(@RequestBody(required = false) String request) {
    LOGGER.info("Received product validation request: {}", request);
    boolean result = true;
    LOGGER.info("Product validation result: {}", result);
    return ResponseEntity.ok(result);
  }
}
