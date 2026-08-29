package com.leadsphere.crm.patterns;

public enum ProductDto {
  ;

  public enum Request {
    ;

    public static final class Create implements Name, Price, Cost, Supplier {
      private String name;
      private Double price;
      private Double cost;
      private String supplier;

      @Override
      public String getName() {
        return name;
      }

      public Create setName(String name) {
        this.name = name;
        return this;
      }

      @Override
      public Double getPrice() {
        return price;
      }

      public Create setPrice(Double price) {
        this.price = price;
        return this;
      }

      @Override
      public Double getCost() {
        return cost;
      }

      public Create setCost(Double cost) {
        this.cost = cost;
        return this;
      }

      @Override
      public String getSupplier() {
        return supplier;
      }

      public Create setSupplier(String supplier) {
        this.supplier = supplier;
        return this;
      }
    }
  }

  public enum Response {
    ;

    public static final class Public implements Id, Name, Price {
      private Long id;
      private String name;
      private Double price;

      @Override
      public Long getId() {
        return id;
      }

      public Public setId(Long id) {
        this.id = id;
        return this;
      }

      @Override
      public String getName() {
        return name;
      }

      public Public setName(String name) {
        this.name = name;
        return this;
      }

      @Override
      public Double getPrice() {
        return price;
      }

      public Public setPrice(Double price) {
        this.price = price;
        return this;
      }

      @Override
      public String toString() {
        return "Public{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
      }
    }

    public static final class Private implements Id, Name, Price, Cost {
      private Long id;
      private String name;
      private Double price;
      private Double cost;

      @Override
      public Long getId() {
        return id;
      }

      public Private setId(Long id) {
        this.id = id;
        return this;
      }

      @Override
      public String getName() {
        return name;
      }

      public Private setName(String name) {
        this.name = name;
        return this;
      }

      @Override
      public Double getPrice() {
        return price;
      }

      public Private setPrice(Double price) {
        this.price = price;
        return this;
      }

      @Override
      public Double getCost() {
        return cost;
      }

      public Private setCost(Double cost) {
        this.cost = cost;
        return this;
      }

      @Override
      public String toString() {
        return "Private{"
            + "id="
            + id
            + ", name='"
            + name
            + '\''
            + ", price="
            + price
            + ", cost="
            + cost
            + '}';
      }
    }
  }

  private interface Id {
    Long getId();
  }

  private interface Name {
    String getName();
  }

  private interface Price {
    Double getPrice();
  }

  private interface Cost {
    Double getCost();
  }

  private interface Supplier {
    String getSupplier();
  }
}
