
package com.leadsphere.crm.patterns;

import java.util.function.Consumer;

public interface Member extends Consumer<DataType> {

  void accept(DataType event);
}
