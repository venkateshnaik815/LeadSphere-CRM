package com.leadsphere.crm.patterns;

import com.iluwatar.featuretoggle.pattern.Service;
import com.iluwatar.featuretoggle.user.User;
import java.util.Properties;
import lombok.Getter;

@Getter
public class PropertiesFeatureToggleVersion implements Service {

  private final boolean enhanced;

  public PropertiesFeatureToggleVersion(final Properties properties) {
    if (properties == null) {
      throw new IllegalArgumentException("No Properties Provided.");
    } else {
      try {
        enhanced = (boolean) properties.get("enhancedWelcome");
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid Enhancement Settings Provided.");
      }
    }
  }

  @Override
  public String getWelcomeMessage(final User user) {

    if (isEnhanced()) {
      return "Welcome " + user + ". You're using the enhanced welcome message.";
    }

    return "Welcome to the application.";
  }
}
