package com.leadsphere.crm.patterns;

import com.iluwatar.bff.dto.DesktopDashboardResponse;
import com.iluwatar.bff.service.AuthService;
import com.iluwatar.bff.service.OrderService;
import com.iluwatar.bff.service.SupplierService;
import java.util.ArrayList;
import java.util.List;

public final class DesktopBff implements ClientBff<DesktopDashboardResponse> {

  private final AuthService authService;

  private final OrderService orderService;

  private final SupplierService supplierService;

  public DesktopBff(
      final AuthService auth, final OrderService orders, final SupplierService suppliers) {
    this.authService = auth;
    this.orderService = orders;
    this.supplierService = suppliers;
  }

  @Override
  public DesktopDashboardResponse getDashboard(final String userId) {
    var user = authService.getUser(userId);
    var orders = orderService.getOrders(userId);

    var orderStatuses =
        orders.stream()
            .map(order -> order.id() + ": " + order.productName() + " [" + order.status() + "]")
            .toList();

    var supplierStockSummaries = new ArrayList<String>();
    for (var order : orders) {
      for (var supplierRecord : supplierService.getSupplierRecords(order.productName())) {
        supplierStockSummaries.add(
            supplierRecord.supplierName()
                + ": "
                + supplierRecord.stockLevel()
                + " units of "
                + order.productName());
      }
    }

    return new DesktopDashboardResponse(
        "Welcome back, " + user.displayName(),
        user.loyaltyTier(),
        List.copyOf(orderStatuses),
        List.copyOf(supplierStockSummaries));
  }
}
