package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserAccount {
  private String userId;

  private String userName;

  private String additionalInfo;
}
