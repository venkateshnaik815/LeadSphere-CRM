
package com.leadsphere.crm.patterns;

public class App {
  public static void main(String[] args) {
    ShoppingFacade shoppingFacade = new ShoppingFacade();

    // Adding items to the shopping cart
    shoppingFacade.addToCart(1);
    shoppingFacade.addToCart(2);

    // Processing the payment with the chosen method
    shoppingFacade.processPayment("cash");

    // Finalizing the order
    shoppingFacade.order();
  }
}
