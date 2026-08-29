package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    // Create and render the HomePageView
    TemplateView homePage = new HomePageView();
    LOGGER.info("Rendering HomePage:");
    homePage.render();

    // Create and render the ContactPageView
    TemplateView contactPage = new ContactPageView();
    LOGGER.info("\nRendering ContactPage:");
    contactPage.render();
  }
}
