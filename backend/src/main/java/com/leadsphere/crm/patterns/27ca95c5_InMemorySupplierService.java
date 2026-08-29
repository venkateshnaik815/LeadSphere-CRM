package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.SupplierRecord;
import com.iluwatar.bff.service.SupplierService;
import java.util.List;
import java.util.Map;

public final class InMemorySupplierService implements SupplierService {

  private final Map<String, List<SupplierRecord>> recordsByProductName;

  public InMemorySupplierService(final Map<String, List<SupplierRecord>> records) {
    this.recordsByProductName = records;
  }

  @Override
  public List<SupplierRecord> getSupplierRecords(final String productName) {
    return recordsByProductName.getOrDefault(productName, List.of());
  }
}
