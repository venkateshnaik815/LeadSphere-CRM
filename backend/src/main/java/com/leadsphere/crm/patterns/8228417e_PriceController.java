package com.leadsphere.crm.patterns;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController {

  private final PriceService priceService;

  @GetMapping("/price")
  public String getPrice() {
    return priceService.getPrice();
  }
}
