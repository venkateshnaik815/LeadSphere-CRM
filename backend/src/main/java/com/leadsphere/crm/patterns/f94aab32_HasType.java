package com.leadsphere.crm.patterns;

import com.iluwatar.abstractdocument.Document;
import com.iluwatar.abstractdocument.domain.enums.Property;
import java.util.Optional;

public interface HasType extends Document {

  default Optional<String> getType() {
    return Optional.ofNullable((String) get(Property.TYPE.toString()));
  }
}
