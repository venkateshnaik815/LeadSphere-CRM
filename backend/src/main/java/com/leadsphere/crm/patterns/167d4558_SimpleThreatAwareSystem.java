package com.leadsphere.crm.patterns;

import com.iluwatar.filterer.domain.Filterer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class SimpleThreatAwareSystem implements ThreatAwareSystem {

  private final String systemId;
  private final List<Threat> issues;

  @Override
  public String systemId() {
    return systemId;
  }

  @Override
  public List<? extends Threat> threats() {
    return new ArrayList<>(issues);
  }

  @Override
  public Filterer<? extends ThreatAwareSystem, ? extends Threat> filtered() {
    return this::filteredGroup;
  }

  private ThreatAwareSystem filteredGroup(Predicate<? super Threat> predicate) {
    return new SimpleThreatAwareSystem(this.systemId, filteredItems(predicate));
  }

  private List<Threat> filteredItems(Predicate<? super Threat> predicate) {
    return this.issues.stream().filter(predicate).toList();
  }
}
