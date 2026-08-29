package com.leadsphere.crm.patterns;

import java.util.function.Predicate;

@FunctionalInterface
public interface Filterer<G, E> {
  G by(Predicate<? super E> predicate);
}
