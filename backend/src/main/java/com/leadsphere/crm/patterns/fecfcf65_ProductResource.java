package com.leadsphere.crm.patterns;

import java.util.List;

public record ProductResource(List<Product> products) {
  public List<ProductDto.Response.Private> getAllProductsForAdmin() {
    return products.stream()
        .map(
            p ->
                new ProductDto.Response.Private()
                    .setId(p.getId())
                    .setName(p.getName())
                    .setCost(p.getCost())
                    .setPrice(p.getPrice()))
        .toList();
  }

  public List<ProductDto.Response.Public> getAllProductsForCustomer() {
    return products.stream()
        .map(
            p ->
                new ProductDto.Response.Public()
                    .setId(p.getId())
                    .setName(p.getName())
                    .setPrice(p.getPrice()))
        .toList();
  }

  public void save(ProductDto.Request.Create createProductDto) {
    products.add(
        Product.builder()
            .id((long) (products.size() + 1))
            .name(createProductDto.getName())
            .supplier(createProductDto.getSupplier())
            .price(createProductDto.getPrice())
            .cost(createProductDto.getCost())
            .build());
  }
}
