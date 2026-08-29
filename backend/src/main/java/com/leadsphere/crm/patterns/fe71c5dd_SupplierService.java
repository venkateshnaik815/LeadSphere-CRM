package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.SupplierRecord;
import java.util.List;

public interface SupplierService {

  List<SupplierRecord> getSupplierRecords(String productName);
}
