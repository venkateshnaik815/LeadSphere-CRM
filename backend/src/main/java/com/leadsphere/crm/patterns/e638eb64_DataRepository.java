
package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {

  private final Map<Integer, String> dataStorage = new HashMap<>();

  @PostConstruct
  public void init() {
    // Injecting dummy data at startup
    dataStorage.put(2, "Initial Dummy Data - two - 2");
    dataStorage.put(3, "Initial Dummy Data - three - 3");
    dataStorage.put(4, "Initial Dummy Data - four - 4");
  }

  public void save(int id, String value) {
    dataStorage.put(id, value);
  }

  public String findById(int id) {
    return dataStorage.getOrDefault(id, "Data not found");
  }

  public void delete(int id) {
    dataStorage.remove(id);
  }

  public Map<Integer, String> findAll() {
    return dataStorage;
  }
}
