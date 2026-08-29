package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashShardManager extends ShardManager {

  @Override
  public int storeData(Data data) {
    var shardId = allocateShard(data);
    var shard = shardMap.get(shardId);
    shard.storeData(data);
    LOGGER.info(data + " is stored in Shard " + shardId);
    return shardId;
  }

  @Override
  protected int allocateShard(Data data) {
    var shardCount = shardMap.size();
    var hash = data.getKey() % shardCount;
    return hash == 0 ? hash + shardCount : hash;
  }
}
