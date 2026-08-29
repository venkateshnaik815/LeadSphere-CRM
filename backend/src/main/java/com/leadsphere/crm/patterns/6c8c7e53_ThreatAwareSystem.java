package com.leadsphere.crm.patterns;

import com.iluwatar.filterer.domain.Filterer;
import java.util.List;

public interface ThreatAwareSystem<T extends Threat> {

  String systemId();

  List<T> threats();

  Filterer<ThreatAwareSystem<T>, T> filtered();
}
