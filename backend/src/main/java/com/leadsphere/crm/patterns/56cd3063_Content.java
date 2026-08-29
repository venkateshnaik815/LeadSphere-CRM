package com.leadsphere.crm.patterns;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Content {
  PRODUCTS("Products - This page lists the company's products."),
  COMPANY("Company - This page displays information about the company.");

  private final String title;

  @Override
  public String toString() {
    return title;
  }
}
