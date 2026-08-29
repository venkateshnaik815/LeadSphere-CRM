package com.leadsphere.crm.patterns;

import java.util.List;

public record MobileDashboardResponse(
    String greeting, int cartItemCount, double cartTotalUsd, List<String> recentOrderSummaries) {}
