package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class Shard {

  @Getter private final int id;

  private final Map<Integer, Data> dataStore;

  public Shard(final int id) {
    this.id = id;
    this.dataStore = new HashMap<>();
  }

  public void storeData(Data data) {
    dataStore.put(data.getKey(), data);
  }

  public void clearData() {
    dataStore.clear();
  }

  public Data getDataById(final int id) {
    return dataStore.get(id);
  }
}
