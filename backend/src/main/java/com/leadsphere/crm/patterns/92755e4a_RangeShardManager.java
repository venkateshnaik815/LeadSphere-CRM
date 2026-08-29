package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeShardManager extends ShardManager {

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
    var type = data.getType();
    return switch (type) {
      case TYPE_1 -> 1;
      case TYPE_2 -> 2;
      case TYPE_3 -> 3;
    };
  }
}
