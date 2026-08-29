package com.leadsphere.crm.patterns;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlPasswordInput;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;

@Slf4j
public class LoginPage extends Page {
  private static final String LOGIN_PAGE_HTML_FILE = "login.html";
  private static final String PAGE_URL = "file:" + AUT_PATH + LOGIN_PAGE_HTML_FILE;

  private HtmlPage page;

  public LoginPage(WebClient webClient) {
    super(webClient);
  }

  public LoginPage navigateToPage() {
    try {
      page = this.webClient.getPage(PAGE_URL);
    } catch (IOException e) {
      LOGGER.error("An error occurred on navigateToPage.", e);
    }
    return this;
  }

  @Override
  public boolean isAt() {
    return "Login".equals(page.getTitleText());
  }

  public LoginPage enterUsername(String username) {
    var usernameInputTextField = (HtmlTextInput) page.getElementById("username");
    usernameInputTextField.setText(username);
    return this;
  }

  public LoginPage enterPassword(String password) {
    var passwordInputPasswordField = (HtmlPasswordInput) page.getElementById("password");
    passwordInputPasswordField.setText(password);
    return this;
  }

  public AlbumListPage login() {
    var loginButton = (HtmlSubmitInput) page.getElementById("loginButton");
    try {
      loginButton.click();
    } catch (IOException e) {
      LOGGER.error("An error occurred on login.", e);
    }
    return new AlbumListPage(webClient);
  }
}
