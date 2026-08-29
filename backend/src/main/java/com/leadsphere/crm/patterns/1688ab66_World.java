package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;

public class World {

  private List<String> countries;
  private final DataFetcher df;

  public World() {
    this.countries = new ArrayList<>();
    this.df = new DataFetcher();
  }

  public List<String> fetch() {
    var data = df.fetch();
    countries = data.isEmpty() ? countries : data;
    return countries;
  }
}
