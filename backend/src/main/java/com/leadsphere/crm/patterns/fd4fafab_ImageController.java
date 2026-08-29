package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ImageController {

  @GetMapping("/image-path")
  public String getImagePath() {
    LOGGER.info("Successfully found image path");
    return "/product-image.png";
  }
}
