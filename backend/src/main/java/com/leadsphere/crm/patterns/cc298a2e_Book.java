package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
  private long id;
  private String title = "";
  private String author = "";
  private long version = 0; // version number

  public Book() {}

  public Book(Book book) {
    this.id = book.id;
    this.title = book.title;
    this.author = book.author;
    this.version = book.version;
  }
}
