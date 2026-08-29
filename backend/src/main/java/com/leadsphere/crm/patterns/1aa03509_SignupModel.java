package com.leadsphere.crm.patterns;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SignupModel {
  private String name;
  private String email;
  private String password;
}
