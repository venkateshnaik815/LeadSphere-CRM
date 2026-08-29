package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TemplateView {

  public final void render() {
    printHeader();
    renderDynamicContent();
    printFooter();
  }

  protected void printHeader() {
    LOGGER.info("Rendering header...");
  }

  protected abstract void renderDynamicContent();

  protected void printFooter() {
    LOGGER.info("Rendering footer...");
  }
}
