package com.leadsphere.crm.patterns;

public class SearchService {

  public String search(String type, String sortBy) {
    return getQuerySummary(type, sortBy, SortOrder.ASC);
  }

  public String search(String type, SortOrder sortOrder) {
    return getQuerySummary(type, "price", sortOrder);
  }

  public String search(ParameterObject parameterObject) {
    return getQuerySummary(
        parameterObject.getType(), parameterObject.getSortBy(), parameterObject.getSortOrder());
  }

  private String getQuerySummary(String type, String sortBy, SortOrder sortOrder) {
    return String.format(
        "Requesting shoes of type \"%s\" sorted by \"%s\" in \"%sending\" order..",
        type, sortBy, sortOrder.getValue());
  }
}
