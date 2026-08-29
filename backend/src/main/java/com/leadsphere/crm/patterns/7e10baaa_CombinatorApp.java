package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CombinatorApp {

  private static final String TEXT =
      """
            It was many and many a year ago,
            In a kingdom by the sea,
            That a maiden there lived whom you may know
            By the name of ANNABEL LEE;
            And this maiden she lived with no other thought
            Than to love and be loved by me.
            I was a child and she was a child,
            In this kingdom by the sea;
            But we loved with a love that was more than love-
            I and my Annabel Lee;
            With a love that the winged seraphs of heaven
            Coveted her and me.""";

  public static void main(String[] args) {
    var queriesOr = new String[] {"many", "Annabel"};
    var finder = Finders.expandedFinder(queriesOr);
    var res = finder.find(text());
    LOGGER.info("the result of expanded(or) query[{}] is {}", queriesOr, res);

    var queriesAnd = new String[] {"Annabel", "my"};
    finder = Finders.specializedFinder(queriesAnd);
    res = finder.find(text());
    LOGGER.info("the result of specialized(and) query[{}] is {}", queriesAnd, res);

    finder = Finders.advancedFinder("it was", "kingdom", "sea");
    res = finder.find(text());
    LOGGER.info("the result of advanced query is {}", res);

    res = Finders.filteredFinder(" was ", "many", "child").find(text());
    LOGGER.info("the result of filtered query is {}", res);
  }

  private static String text() {

    return TEXT;
  }
}
