
package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {

  private String data;

  private Session session;
}
