package com.leadsphere.crm.patterns;

import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientPropertiesBean implements Serializable {

  private static final String WORLD_PARAM = "world";
  private static final String SCIENCE_PARAM = "sci";
  private static final String SPORTS_PARAM = "sport";
  private static final String BUSINESS_PARAM = "bus";
  private static final String NAME_PARAM = "name";

  private static final String DEFAULT_NAME = "DEFAULT_NAME";
  private boolean worldNewsInterest = true;
  private boolean sportsInterest = true;
  private boolean businessInterest = true;
  private boolean scienceNewsInterest = true;
  private String name = DEFAULT_NAME;

  public ClientPropertiesBean(HttpServletRequest req) {
    worldNewsInterest = Boolean.parseBoolean(req.getParameter(WORLD_PARAM));
    sportsInterest = Boolean.parseBoolean(req.getParameter(SPORTS_PARAM));
    businessInterest = Boolean.parseBoolean(req.getParameter(BUSINESS_PARAM));
    scienceNewsInterest = Boolean.parseBoolean(req.getParameter(SCIENCE_PARAM));
    String tempName = req.getParameter(NAME_PARAM);
    if (tempName == null || tempName.equals("")) {
      tempName = DEFAULT_NAME;
    }
    name = tempName;
  }
}
