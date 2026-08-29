package com.leadsphere.crm.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    var context = SpringApplication.run(App.class, args);
    if (args.length > 0 && "test".equals(args[0])) {
      // Close the context immediately during tests to prevent Tomcat/background threads from
      // hanging the JVM
      context.close();
    }
  }
}
