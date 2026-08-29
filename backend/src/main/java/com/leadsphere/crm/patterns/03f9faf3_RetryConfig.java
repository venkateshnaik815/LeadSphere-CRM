package com.leadsphere.crm.patterns;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class RetryConfig {

  @Value("${retry.backoff.period:2000}")
  private long backOffPeriod;

  @Value("${retry.max.attempts:3}")
  private int maxAttempts;

  @Bean
  public RetryTemplate retryTemplate() {
    RetryTemplate retryTemplate = new RetryTemplate();

    FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(backOffPeriod); // wait 2 seconds between retries
    retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
    retryPolicy.setMaxAttempts(maxAttempts); // retry a max of 3 attempts
    retryTemplate.setRetryPolicy(retryPolicy);

    return retryTemplate;
  }
}
