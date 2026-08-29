
package com.leadsphere.crm.patterns;

import java.io.Serial;
import org.springframework.stereotype.Component;

@Component
public class CakeBakingException extends Exception {

  @Serial private static final long serialVersionUID = 1L;

  public CakeBakingException() {}

  public CakeBakingException(String message) {
    super(message);
  }
}
