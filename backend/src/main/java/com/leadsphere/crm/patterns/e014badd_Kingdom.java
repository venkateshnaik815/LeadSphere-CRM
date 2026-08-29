package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Kingdom {

  private King king;
  private Castle castle;
  private Army army;

  public static class FactoryMaker {

    public enum KingdomType {
      ELF,
      ORC
    }

    public static KingdomFactory makeFactory(KingdomType type) {
      return switch (type) {
        case ELF -> new ElfKingdomFactory();
        case ORC -> new OrcKingdomFactory();
      };
    }
  }
}
