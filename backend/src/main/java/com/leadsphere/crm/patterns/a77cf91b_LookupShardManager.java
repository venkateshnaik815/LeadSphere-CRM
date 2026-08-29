package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LookupShardManager extends ShardManager {

  private final Map<Integer, Integer> lookupMap = new HashMap<>();

  @Override
  public int storeData(Data data) {
    var shardId = allocateShard(data);
    lookupMap.put(data.getKey(), shardId);
    var shard = shardMap.get(shardId);
    shard.storeData(data);
    LOGGER.info(data + " is stored in Shard " + shardId);
    return shardId;
  }

  @Override
  protected int allocateShard(Data data) {
    var key = data.getKey();
    if (lookupMap.containsKey(key)) {
      return lookupMap.get(key);
    } else {
      var shardCount = shardMap.size();
      return new SecureRandom().nextInt(shardCount - 1) + 1;
    }
  }
}
