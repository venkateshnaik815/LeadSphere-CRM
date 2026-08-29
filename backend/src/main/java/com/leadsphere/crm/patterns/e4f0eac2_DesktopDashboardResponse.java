package com.leadsphere.crm.patterns;

import java.util.List;

public record DesktopDashboardResponse(
    String greeting,
    String loyaltyTier,
    List<String> orderStatuses,
    List<String> supplierStockSummaries) {}
