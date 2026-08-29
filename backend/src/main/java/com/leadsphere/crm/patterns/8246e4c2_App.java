package com.leadsphere.crm.patterns;

import com.iluwatar.specification.creature.Creature;
import com.iluwatar.specification.creature.Dragon;
import com.iluwatar.specification.creature.Goblin;
import com.iluwatar.specification.creature.KillerBee;
import com.iluwatar.specification.creature.Octopus;
import com.iluwatar.specification.creature.Shark;
import com.iluwatar.specification.creature.Troll;
import com.iluwatar.specification.property.Color;
import com.iluwatar.specification.property.Movement;
import com.iluwatar.specification.selector.ColorSelector;
import com.iluwatar.specification.selector.MassEqualSelector;
import com.iluwatar.specification.selector.MassGreaterThanSelector;
import com.iluwatar.specification.selector.MassSmallerThanOrEqSelector;
import com.iluwatar.specification.selector.MovementSelector;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    // initialize creatures list
    var creatures =
        List.of(
            new Goblin(), new Octopus(), new Dragon(), new Shark(), new Troll(), new KillerBee());
    // so-called "hard-coded" specification
    LOGGER.info("Demonstrating hard-coded specification :");
    // find all walking creatures
    LOGGER.info("Find all walking creatures");
    print(creatures, new MovementSelector(Movement.WALKING));
    // find all dark creatures
    LOGGER.info("Find all dark creatures");
    print(creatures, new ColorSelector(Color.DARK));
    LOGGER.info("\n");
    // so-called "parameterized" specification
    LOGGER.info("Demonstrating parameterized specification :");
    // find all creatures heavier than 500kg
    LOGGER.info("Find all creatures heavier than 600kg");
    print(creatures, new MassGreaterThanSelector(600.0));
    // find all creatures heavier than 500kg
    LOGGER.info("Find all creatures lighter than or weighing exactly 500kg");
    print(creatures, new MassSmallerThanOrEqSelector(500.0));
    LOGGER.info("\n");
    // so-called "composite" specification
    LOGGER.info("Demonstrating composite specification :");
    // find all red and flying creatures
    LOGGER.info("Find all red and flying creatures");
    var redAndFlying = new ColorSelector(Color.RED).and(new MovementSelector(Movement.FLYING));
    print(creatures, redAndFlying);
    // find all creatures dark or red, non-swimming, and heavier than or equal to 400kg
    LOGGER.info("Find all scary creatures");
    var scaryCreaturesSelector =
        new ColorSelector(Color.DARK)
            .or(new ColorSelector(Color.RED))
            .and(new MovementSelector(Movement.SWIMMING).not())
            .and(new MassGreaterThanSelector(400.0).or(new MassEqualSelector(400.0)));
    print(creatures, scaryCreaturesSelector);
  }

  private static void print(List<? extends Creature> creatures, Predicate<Creature> selector) {
    creatures.stream().filter(selector).map(Objects::toString).forEach(LOGGER::info);
  }
}
