package com.leadsphere.crm.patterns;

import com.iluwatar.bff.dto.MobileDashboardResponse;
import com.iluwatar.bff.service.AuthService;
import com.iluwatar.bff.service.CartService;
import com.iluwatar.bff.service.OrderService;
import java.util.List;

public final class MobileBff implements ClientBff<MobileDashboardResponse> {

  private static final int MAX_RECENT_ORDERS = 3;

  private final AuthService authService;

  private final CartService cartService;

  private final OrderService orderService;

  public MobileBff(final AuthService auth, final CartService cart, final OrderService orders) {
    this.authService = auth;
    this.cartService = cart;
    this.orderService = orders;
  }

  @Override
  public MobileDashboardResponse getDashboard(final String userId) {
    var user = authService.getUser(userId);
    var cart = cartService.getCart(userId);
    var orders = orderService.getOrders(userId);

    var cartTotal = cart.stream().mapToDouble(item -> item.lineTotal()).sum();
    var recentOrderSummaries =
        orders.stream()
            .limit(MAX_RECENT_ORDERS)
            .map(order -> order.productName() + " (" + order.status() + ")")
            .toList();

    return new MobileDashboardResponse(
        "Hi " + user.displayName() + "!",
        cart.size(),
        cartTotal,
        List.copyOf(recentOrderSummaries));
  }
}
