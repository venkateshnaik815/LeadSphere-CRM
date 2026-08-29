package com.leadsphere.crm.patterns;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;
  private double price;
  @ManyToOne private Author author;

  public Book(String title, double price, Author author) {
    this.title = title;
    this.price = price;
    this.author = author;
  }

  protected Book() {}
}
