package com.leadsphere.crm.patterns;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@Setter
public class GarbageCollectionHealthIndicator implements HealthIndicator {

  @Value("${memory.usage.threshold:0.8}")
  private double memoryUsageThreshold;

  @Override
  public Health health() {
    List<GarbageCollectorMXBean> gcBeans = getGarbageCollectorMxBeans();
    List<MemoryPoolMXBean> memoryPoolMxBeans = getMemoryPoolMxBeans();
    Map<String, Map<String, String>> gcDetails = new HashMap<>();

    for (GarbageCollectorMXBean gcBean : gcBeans) {
      Map<String, String> collectorDetails = createCollectorDetails(gcBean, memoryPoolMxBeans);
      gcDetails.put(gcBean.getName(), collectorDetails);
    }

    return Health.up().withDetails(gcDetails).build();
  }

  private Map<String, String> createCollectorDetails(
      GarbageCollectorMXBean gcBean, List<MemoryPoolMXBean> memoryPoolMxBeans) {
    Map<String, String> collectorDetails = new HashMap<>();
    long count = gcBean.getCollectionCount();
    long time = gcBean.getCollectionTime();
    collectorDetails.put("count", String.format("%d", count));
    collectorDetails.put("time", String.format("%dms", time));

    String[] memoryPoolNames = gcBean.getMemoryPoolNames();
    List<String> memoryPoolNamesList = Arrays.asList(memoryPoolNames);
    if (!memoryPoolNamesList.isEmpty()) {
      addMemoryPoolDetails(collectorDetails, memoryPoolMxBeans, memoryPoolNamesList);
    } else {
      LOGGER.error("Garbage collector '{}' does not have any memory pools", gcBean.getName());
    }

    return collectorDetails;
  }

  private void addMemoryPoolDetails(
      Map<String, String> collectorDetails,
      List<MemoryPoolMXBean> memoryPoolMxBeans,
      List<String> memoryPoolNamesList) {
    for (MemoryPoolMXBean memoryPoolmxbean : memoryPoolMxBeans) {
      if (memoryPoolNamesList.contains(memoryPoolmxbean.getName())) {
        double memoryUsage =
            memoryPoolmxbean.getUsage().getUsed() / (double) memoryPoolmxbean.getUsage().getMax();
        if (memoryUsage > memoryUsageThreshold) {
          collectorDetails.put(
              "warning",
              String.format(
                  "Memory pool '%s' usage is high (%2f%%)",
                  memoryPoolmxbean.getName(), memoryUsage));
        }

        collectorDetails.put(
            "memoryPools", String.format("%s: %s%%", memoryPoolmxbean.getName(), memoryUsage));
      }
    }
  }

  protected List<GarbageCollectorMXBean> getGarbageCollectorMxBeans() {
    return ManagementFactory.getGarbageCollectorMXBeans();
  }

  protected List<MemoryPoolMXBean> getMemoryPoolMxBeans() {
    return ManagementFactory.getMemoryPoolMXBeans();
  }
}
