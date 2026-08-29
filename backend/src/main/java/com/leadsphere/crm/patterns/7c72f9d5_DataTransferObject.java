package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DataTransferObject {

  private final Notification notification = new Notification();
}
