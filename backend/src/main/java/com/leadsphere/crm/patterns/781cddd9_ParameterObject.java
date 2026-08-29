package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterObject {

  public static final String DEFAULT_SORT_BY = "price";

  public static final SortOrder DEFAULT_SORT_ORDER = SortOrder.ASC;

  private String type;

  private String sortBy = DEFAULT_SORT_BY;

  private SortOrder sortOrder = DEFAULT_SORT_ORDER;

  private ParameterObject(Builder builder) {
    setType(builder.type);
    setSortBy(builder.sortBy != null && !builder.sortBy.isBlank() ? builder.sortBy : sortBy);
    setSortOrder(builder.sortOrder != null ? builder.sortOrder : sortOrder);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return String.format(
        "ParameterObject[type='%s', sortBy='%s', sortOrder='%s']", type, sortBy, sortOrder);
  }

  public static final class Builder {

    private String type;
    private String sortBy;
    private SortOrder sortOrder;

    private Builder() {}

    public Builder withType(String type) {
      this.type = type;
      return this;
    }

    public Builder sortBy(String sortBy) {
      this.sortBy = sortBy;
      return this;
    }

    public Builder sortOrder(SortOrder sortOrder) {
      this.sortOrder = sortOrder;
      return this;
    }

    public ParameterObject build() {
      return new ParameterObject(this);
    }
  }
}
