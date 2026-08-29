package com.leadsphere.crm.patterns;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class SignupView {

  public String display() {
    LOGGER.info("display signup front page");
    return "/signup";
  }

  public String redirect(SignupModel form) {
    LOGGER.info(
        "Redirect to user page with " + "name " + form.getName() + " email " + form.getEmail());
    return "redirect:/user";
  }
}
