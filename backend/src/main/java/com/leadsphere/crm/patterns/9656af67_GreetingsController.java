package com.leadsphere.crm.patterns;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

  @GetMapping("/greeting")
  public String getGreeting() {
    return "Hello";
  }
}
