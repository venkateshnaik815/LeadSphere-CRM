
package com.leadsphere.crm.patterns;

import java.math.BigDecimal;
import java.time.LocalDate;

public class App {
  public static void main(String[] args) {
    // Raw Product data (no formatting, no UI tags)
    var product =
        new Product(
            "Design patterns book", new BigDecimal("18.90"), LocalDate.of(2025, 4, 19), true);

    // Create view, viewHelper and viewHelper
    var helper = new ProductViewHelper();
    var view = new ConsoleProductView();
    var controller = new ProductController(helper, view);

    // Handle “request”
    controller.handle(product);
  }
}
