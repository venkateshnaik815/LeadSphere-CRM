package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

  private final OrderService orderService;

  public OrderController(final OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/order")
  public ResponseEntity<String> processOrder(@RequestBody(required = false) String request) {
    LOGGER.info("Received order request: {}", request);
    var result = orderService.processOrder();
    LOGGER.info("Order processed result: {}", result);
    return ResponseEntity.ok(result);
  }
}
