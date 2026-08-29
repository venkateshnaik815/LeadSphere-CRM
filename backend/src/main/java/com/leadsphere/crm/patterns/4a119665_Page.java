package com.leadsphere.crm.patterns;

import org.htmlunit.WebClient;

public abstract class Page {

  public static final String AUT_PATH = "../sample-application/src/main/resources/sample-ui/";

  protected final WebClient webClient;

  public Page(WebClient webClient) {
    this.webClient = webClient;
  }

  public abstract boolean isAt();
}
