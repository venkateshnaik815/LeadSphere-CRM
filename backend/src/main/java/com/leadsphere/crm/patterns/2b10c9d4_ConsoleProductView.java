package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleProductView implements View<ProductViewModel> {
  @Override
  public void render(ProductViewModel productViewModel) {
    LOGGER.info(productViewModel.toString());
  }
}
