package com.leadsphere.crm.patterns;

public class ProductController {

  private final ViewHelper<Product, ProductViewModel> viewHelper;
  private final View<ProductViewModel> view;

  public ProductController(
      ViewHelper<Product, ProductViewModel> viewHelper, View<ProductViewModel> view) {
    this.viewHelper = viewHelper;
    this.view = view;
  }

  public void handle(Product product) {
    view.render(viewHelper.prepare(product));
  }
}
