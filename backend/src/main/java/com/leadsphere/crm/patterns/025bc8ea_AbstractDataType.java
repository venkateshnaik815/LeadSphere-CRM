
package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDataType implements DataType {

  private DataBus dataBus;
}
