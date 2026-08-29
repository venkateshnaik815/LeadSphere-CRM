package com.leadsphere.crm.patterns;

import com.iluwatar.filterer.domain.Filterer;
import java.util.List;
import java.util.function.Predicate;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class SimpleProbabilisticThreatAwareSystem implements ProbabilisticThreatAwareSystem {

  private final String systemId;
  private final List<ProbableThreat> threats;

  @Override
  public String systemId() {
    return systemId;
  }

  @Override
  public List<? extends ProbableThreat> threats() {
    return threats;
  }

  @Override
  public Filterer<? extends ProbabilisticThreatAwareSystem, ? extends ProbableThreat> filtered() {
    return this::filteredGroup;
  }

  private ProbabilisticThreatAwareSystem filteredGroup(
      final Predicate<? super ProbableThreat> predicate) {
    return new SimpleProbabilisticThreatAwareSystem(this.systemId, filteredItems(predicate));
  }

  private List<ProbableThreat> filteredItems(final Predicate<? super ProbableThreat> predicate) {
    return this.threats.stream().filter(predicate).toList();
  }
}
