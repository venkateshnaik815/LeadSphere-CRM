package com.leadsphere.crm.patterns;

import com.iluwatar.filterer.threat.ProbableThreat;
import com.iluwatar.filterer.threat.SimpleProbabilisticThreatAwareSystem;
import com.iluwatar.filterer.threat.SimpleProbableThreat;
import com.iluwatar.filterer.threat.SimpleThreat;
import com.iluwatar.filterer.threat.SimpleThreatAwareSystem;
import com.iluwatar.filterer.threat.Threat;
import com.iluwatar.filterer.threat.ThreatAwareSystem;
import com.iluwatar.filterer.threat.ThreatType;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    filteringSimpleThreats();
    filteringSimpleProbableThreats();
  }

  private static void filteringSimpleProbableThreats() {
    LOGGER.info("### Filtering ProbabilisticThreatAwareSystem by probability ###");

    var trojanArcBomb = new SimpleProbableThreat("Trojan-ArcBomb", 1, ThreatType.TROJAN, 0.99);
    var rootkit = new SimpleProbableThreat("Rootkit-Kernel", 2, ThreatType.ROOTKIT, 0.8);

    List<ProbableThreat> probableThreats = List.of(trojanArcBomb, rootkit);

    var probabilisticThreatAwareSystem =
        new SimpleProbabilisticThreatAwareSystem("Sys-1", probableThreats);

    LOGGER.info(
        "Filtering ProbabilisticThreatAwareSystem. Initial : " + probabilisticThreatAwareSystem);

    // Filtering using filterer
    var filteredThreatAwareSystem =
        probabilisticThreatAwareSystem
            .filtered()
            .by(probableThreat -> Double.compare(probableThreat.probability(), 0.99) == 0);

    LOGGER.info("Filtered by probability = 0.99 : " + filteredThreatAwareSystem);
  }

  private static void filteringSimpleThreats() {
    LOGGER.info("### Filtering ThreatAwareSystem by ThreatType ###");

    var rootkit = new SimpleThreat(ThreatType.ROOTKIT, 1, "Simple-Rootkit");
    var trojan = new SimpleThreat(ThreatType.TROJAN, 2, "Simple-Trojan");
    List<Threat> threats = List.of(rootkit, trojan);

    var threatAwareSystem = new SimpleThreatAwareSystem("Sys-1", threats);

    LOGGER.info("Filtering ThreatAwareSystem. Initial : " + threatAwareSystem);

    // Filtering using Filterer
    var rootkitThreatAwareSystem =
        threatAwareSystem.filtered().by(threat -> threat.type() == ThreatType.ROOTKIT);

    LOGGER.info("Filtered by threatType = ROOTKIT : " + rootkitThreatAwareSystem);
  }
}
