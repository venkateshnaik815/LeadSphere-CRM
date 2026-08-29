package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ShardManager {

  protected Map<Integer, Shard> shardMap;

  public ShardManager() {
    shardMap = new HashMap<>();
  }

  public boolean addNewShard(final Shard shard) {
    var shardId = shard.getId();
    if (!shardMap.containsKey(shardId)) {
      shardMap.put(shardId, shard);
      return true;
    } else {
      return false;
    }
  }

  public boolean removeShardById(final int shardId) {
    if (shardMap.containsKey(shardId)) {
      shardMap.remove(shardId);
      return true;
    } else {
      return false;
    }
  }

  public Shard getShardById(final int shardId) {
    return shardMap.get(shardId);
  }

  public abstract int storeData(final Data data);

  protected abstract int allocateShard(final Data data);
}
