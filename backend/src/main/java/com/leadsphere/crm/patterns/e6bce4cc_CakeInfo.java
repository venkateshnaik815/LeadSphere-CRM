
package com.leadsphere.crm.patterns;

import java.util.List;

public class CakeInfo {

  public final Long id;
  public final CakeToppingInfo cakeToppingInfo;
  public final List<CakeLayerInfo> cakeLayerInfos;

  public CakeInfo(Long id, CakeToppingInfo cakeToppingInfo, List<CakeLayerInfo> cakeLayerInfos) {
    this.id = id;
    this.cakeToppingInfo = cakeToppingInfo;
    this.cakeLayerInfos = cakeLayerInfos;
  }

  public CakeInfo(CakeToppingInfo cakeToppingInfo, List<CakeLayerInfo> cakeLayerInfos) {
    this.id = null;
    this.cakeToppingInfo = cakeToppingInfo;
    this.cakeLayerInfos = cakeLayerInfos;
  }

  public int calculateTotalCalories() {
    var total = cakeToppingInfo != null ? cakeToppingInfo.calories : 0;
    total += cakeLayerInfos.stream().mapToInt(c -> c.calories).sum();
    return total;
  }

  @Override
  public String toString() {
    return String.format(
        "CakeInfo id=%d topping=%s layers=%s totalCalories=%d",
        id, cakeToppingInfo, cakeLayerInfos, calculateTotalCalories());
  }
}
