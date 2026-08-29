package com.leadsphere.crm.patterns;

import com.iluwatar.abstractdocument.Document;
import com.iluwatar.abstractdocument.domain.enums.Property;
import java.util.Optional;

public interface HasModel extends Document {

  default Optional<String> getModel() {
    return Optional.ofNullable((String) get(Property.MODEL.toString()));
  }
}
