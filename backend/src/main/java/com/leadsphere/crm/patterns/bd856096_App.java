package com.leadsphere.crm.patterns;

import com.iluwatar.bff.bff.DesktopBff;
import com.iluwatar.bff.bff.MobileBff;
import com.iluwatar.bff.model.CartItem;
import com.iluwatar.bff.model.Order;
import com.iluwatar.bff.model.Product;
import com.iluwatar.bff.model.SupplierRecord;
import com.iluwatar.bff.model.User;
import com.iluwatar.bff.service.impl.InMemoryAuthService;
import com.iluwatar.bff.service.impl.InMemoryCartService;
import com.iluwatar.bff.service.impl.InMemoryOrderService;
import com.iluwatar.bff.service.impl.InMemorySupplierService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  private static final String USER_ID = "u-1";

  private static final String PRODUCT_ID = "p-42";

  private static final double DEMO_PRICE_USD = 79.99;

  private static final int DEMO_STOCK_LEVEL = 120;

  private App() {
    // utility class
  }

  public static void main(final String[] args) {
    // shared downstream microservices, as drawn in the pattern diagram
    var authService = new InMemoryAuthService(Map.of(USER_ID, new User(USER_ID, "Alice", "GOLD")));

    var product = new Product(PRODUCT_ID, "Wireless Headphones", DEMO_PRICE_USD);
    var cartService = new InMemoryCartService(Map.of(USER_ID, List.of(new CartItem(product, 2))));

    var orderService =
        new InMemoryOrderService(
            Map.of(
                USER_ID,
                List.of(
                    new Order("o-1", "Wireless Headphones", "DELIVERED"),
                    new Order("o-2", "USB-C Cable", "IN_TRANSIT"))));

    var supplierService =
        new InMemorySupplierService(
            Map.of(
                "Wireless Headphones",
                List.of(new SupplierRecord(PRODUCT_ID, "Acme Audio Co.", DEMO_STOCK_LEVEL))));

    // client-specific BFFs, each calling only the services their client needs
    var mobileBff = new MobileBff(authService, cartService, orderService);
    var desktopBff = new DesktopBff(authService, orderService, supplierService);

    var mobileResponse = mobileBff.getDashboard(USER_ID);
    LOGGER.info("Mobile BFF response: {}", mobileResponse);

    var desktopResponse = desktopBff.getDashboard(USER_ID);
    LOGGER.info("Desktop BFF response: {}", desktopResponse);
  }
}
