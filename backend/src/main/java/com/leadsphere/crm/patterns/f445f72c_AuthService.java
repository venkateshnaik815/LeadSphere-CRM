package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.User;

public interface AuthService {

  User getUser(String userId);
}
