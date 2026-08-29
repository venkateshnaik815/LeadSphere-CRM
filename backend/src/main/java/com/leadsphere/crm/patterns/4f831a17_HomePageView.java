package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomePageView extends TemplateView {
  @Override
  protected void renderDynamicContent() {
    LOGGER.info("Welcome to the Home Page!");
  }
}
