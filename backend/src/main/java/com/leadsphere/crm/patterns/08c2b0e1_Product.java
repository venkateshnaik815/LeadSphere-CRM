package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Product {
  private Long id;
  private String name;
  private Double price;
  private Double cost;
  private String supplier;

  @Override
  public String toString() {
    return "Product{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", price="
        + price
        + ", cost="
        + cost
        + ", supplier='"
        + supplier
        + '\''
        + '}';
  }
}
