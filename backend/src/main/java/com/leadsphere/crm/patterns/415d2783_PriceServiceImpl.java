package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

  @Override
  public String getPrice() {
    LOGGER.info("Successfully found price info");
    return "20";
  }
}
