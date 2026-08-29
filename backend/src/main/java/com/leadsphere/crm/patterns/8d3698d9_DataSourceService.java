
package com.leadsphere.crm.patterns;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataSourceService {

  private static final Logger log = LoggerFactory.getLogger(DataSourceService.class);

  private final DataRepository repository;
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

  @org.springframework.beans.factory.annotation.Autowired
  public DataSourceService(DataRepository repository) {
    this(repository, true);
  }

  public DataSourceService(DataRepository repository, boolean startScheduler) {
    this.repository = repository;
    if (startScheduler) {
      scheduleDataGeneration();
    }
  }

  private void scheduleDataGeneration() {
    Random random = new Random();
    scheduler.scheduleAtFixedRate(
        () -> {
          int id = random.nextInt(100); // Random ID
          String value = "Auto-Data-" + id;
          this.addData(id, value);
          log.info("🔵 Data Added: {} -> {}", id, value);
        },
        0,
        3,
        TimeUnit.SECONDS);
  }

  public void addData(int id, String value) {
    repository.save(id, value);
  }

  public String getData(int id) {
    return repository.findById(id);
  }

  public void removeData(int id) {
    repository.delete(id);
  }

  public Map<Integer, String> getAllData() {
    return repository.findAll();
  }

  @javax.annotation.PreDestroy
  public void shutdown() {
    scheduler.shutdown();
  }
}
