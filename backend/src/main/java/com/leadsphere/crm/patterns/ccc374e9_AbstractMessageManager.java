package com.leadsphere.crm.patterns;

import java.util.Map;

public abstract class AbstractMessageManager implements MessageManager {

  protected Map<Integer, Instance> instanceMap;

  public AbstractMessageManager(Map<Integer, Instance> instanceMap) {
    this.instanceMap = instanceMap;
  }

  protected Instance findNextInstance(int currentId) {
    Instance result = null;
    var candidateList =
        instanceMap.keySet().stream()
            .filter((i) -> i > currentId && instanceMap.get(i).isAlive())
            .sorted()
            .toList();
    if (candidateList.isEmpty()) {
      var index =
          instanceMap.keySet().stream()
              .filter((i) -> instanceMap.get(i).isAlive())
              .sorted()
              .toList()
              .get(0);
      result = instanceMap.get(index);
    } else {
      var index = candidateList.get(0);
      result = instanceMap.get(index);
    }
    return result;
  }
}
