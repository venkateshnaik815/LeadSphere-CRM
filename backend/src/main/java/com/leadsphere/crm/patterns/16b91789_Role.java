package com.leadsphere.crm.patterns;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Role {
  BORROWER(BorrowerRole.class),
  INVESTOR(InvestorRole.class);

  private final Class<? extends CustomerRole> typeCst;

  Role(Class<? extends CustomerRole> typeCst) {
    this.typeCst = typeCst;
  }

  private static final Logger logger = LoggerFactory.getLogger(Role.class);

  @SuppressWarnings("unchecked")
  public <T extends CustomerRole> Optional<T> instance() {
    var typeCst = this.typeCst;
    try {
      return (Optional<T>) Optional.of(typeCst.getDeclaredConstructor().newInstance());
    } catch (InstantiationException
        | IllegalAccessException
        | NoSuchMethodException
        | InvocationTargetException e) {
      logger.error("error creating an object", e);
    }
    return Optional.empty();
  }
}
