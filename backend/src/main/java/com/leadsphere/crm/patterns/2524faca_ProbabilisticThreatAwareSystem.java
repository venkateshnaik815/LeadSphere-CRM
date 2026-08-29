package com.leadsphere.crm.patterns;

import com.iluwatar.filterer.domain.Filterer;
import java.util.List;

public interface ProbabilisticThreatAwareSystem extends ThreatAwareSystem {

  @Override
  List<? extends ProbableThreat> threats();

  @Override
  Filterer<? extends ProbabilisticThreatAwareSystem, ? extends ProbableThreat> filtered();
}
