package com.leadsphere.crm.patterns;

import java.util.Map;
import java.util.Random;

public abstract class FrontendComponent {

  public static final Random random = new Random();

  public String fetchData(Map<String, String> params) {
    try {
      // Simulate delay in fetching data (e.g., network latency)
      Thread.sleep(random.nextInt(1000));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    // Fetch and return the data based on the given parameters
    return getData(params);
  }

  protected abstract String getData(Map<String, String> params);
}
