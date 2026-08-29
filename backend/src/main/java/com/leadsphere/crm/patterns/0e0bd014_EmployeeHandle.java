package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Order;
import com.iluwatar.commander.Service;
import com.iluwatar.commander.exceptions.DatabaseUnavailableException;

public class EmployeeHandle extends Service {

  public EmployeeHandle(EmployeeDatabase db, Exception... exc) {
    super(db, exc);
  }

  public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
    return updateDb(parameters[0]);
  }

  protected String updateDb(Object... parameters) throws DatabaseUnavailableException {
    var o = (Order) parameters[0];
    if (database.get(o.id) == null) {
      database.add(o);
      return o.id; // true rcvd - change addedToEmployeeHandle to true else don't do anything
    }
    return null;
  }
}
