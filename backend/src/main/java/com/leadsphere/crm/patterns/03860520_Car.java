package com.leadsphere.crm.patterns;

import com.iluwatar.abstractdocument.AbstractDocument;
import java.util.Map;

public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts {

  public Car(Map<String, Object> properties) {
    super(properties);
  }
}
