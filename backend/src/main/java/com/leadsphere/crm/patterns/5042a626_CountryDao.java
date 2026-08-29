
package com.leadsphere.crm.patterns;

import java.io.IOException;

public interface CountryDao {
  int insertCountry() throws IOException;

  int selectCountry() throws IOException, ClassNotFoundException;
}
