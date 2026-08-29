package com.leadsphere.crm.patterns;

public record Video(
    Integer id,
    String title,
    Integer length,
    String description,
    String director,
    String language) {
  @Override
  public String toString() {
    return "{"
        + "\"id\": "
        + id
        + ","
        + "\"title\": \""
        + title
        + "\","
        + "\"length\": "
        + length
        + ","
        + "\"description\": \""
        + description
        + "\","
        + "\"director\": \""
        + director
        + "\","
        + "\"language\": \""
        + language
        + "\""
        + "}";
  }
}
