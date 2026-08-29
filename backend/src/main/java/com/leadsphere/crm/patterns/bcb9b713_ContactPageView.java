package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContactPageView extends TemplateView {

  @Override
  protected void renderDynamicContent() {
    LOGGER.info("Contact us at: contact@example.com");
  }
}
