package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Getter
public class ReceiptDto implements ReceiptViewModel {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptDto.class);

  private final Double price;

  @Override
  public void show() {
    LOGGER.info(String.format("Receipt: %s paid", price));
  }
}
