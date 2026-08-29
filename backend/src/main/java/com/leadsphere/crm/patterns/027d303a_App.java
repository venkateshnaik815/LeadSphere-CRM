package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    MmaBantamweightFighter fighter1 =
        new MmaBantamweightFighter("Joe", "Johnson", "The Geek", "Muay Thai");
    MmaBantamweightFighter fighter2 =
        new MmaBantamweightFighter("Ed", "Edwards", "The Problem Solver", "Judo");
    fighter1.fight(fighter2);

    MmaHeavyweightFighter fighter3 =
        new MmaHeavyweightFighter("Dave", "Davidson", "The Bug Smasher", "Kickboxing");
    MmaHeavyweightFighter fighter4 =
        new MmaHeavyweightFighter("Jack", "Jackson", "The Pragmatic", "Brazilian Jiu-Jitsu");
    fighter3.fight(fighter4);
  }
}
