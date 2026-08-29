package com.leadsphere.crm.patterns;

import com.iluwatar.featuretoggle.pattern.Service;
import com.iluwatar.featuretoggle.pattern.propertiesversion.PropertiesFeatureToggleVersion;
import com.iluwatar.featuretoggle.pattern.tieredversion.TieredFeatureToggleVersion;
import com.iluwatar.featuretoggle.user.User;
import com.iluwatar.featuretoggle.user.UserGroup;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    // Demonstrates the PropertiesFeatureToggleVersion running with properties
    // that set the feature toggle to enabled.

    final var properties = new Properties();
    properties.put("enhancedWelcome", true);
    var service = new PropertiesFeatureToggleVersion(properties);
    final var welcomeMessage = service.getWelcomeMessage(new User("Jamie No Code"));
    LOGGER.info(welcomeMessage);

    // Demonstrates the PropertiesFeatureToggleVersion running with properties
    // that set the feature toggle to disabled. Note the difference in the printed welcome message
    // where the username is not included.

    final var turnedOff = new Properties();
    turnedOff.put("enhancedWelcome", false);
    var turnedOffService = new PropertiesFeatureToggleVersion(turnedOff);
    final var welcomeMessageturnedOff =
        turnedOffService.getWelcomeMessage(new User("Jamie No Code"));
    LOGGER.info(welcomeMessageturnedOff);

    // Demonstrates the TieredFeatureToggleVersion setup with
    // two users: one on the free tier and the other on the paid tier. When the
    // Service#getWelcomeMessage(User) method is called with the paid user, the welcome
    // message includes their username. In contrast, calling the same service with the free tier
    // user results
    // in a more generic welcome message without the username.

    var service2 = new TieredFeatureToggleVersion();

    final var paidUser = new User("Jamie Coder");
    final var freeUser = new User("Alan Defect");

    UserGroup.addUserToPaidGroup(paidUser);
    UserGroup.addUserToFreeGroup(freeUser);

    final var welcomeMessagePaidUser = service2.getWelcomeMessage(paidUser);
    final var welcomeMessageFreeUser = service2.getWelcomeMessage(freeUser);
    LOGGER.info(welcomeMessageFreeUser);
    LOGGER.info(welcomeMessagePaidUser);
  }
}
