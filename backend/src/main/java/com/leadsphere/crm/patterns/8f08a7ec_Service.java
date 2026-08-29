package com.leadsphere.crm.patterns;

import com.iluwatar.featuretoggle.user.User;

public interface Service {

  String getWelcomeMessage(User user);

  boolean isEnhanced();
}
