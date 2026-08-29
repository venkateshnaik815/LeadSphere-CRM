package com.leadsphere.crm.patterns;

import com.iluwatar.datatransfer.customer.CustomerDto;
import com.iluwatar.datatransfer.customer.CustomerResource;
import com.iluwatar.datatransfer.product.Product;
import com.iluwatar.datatransfer.product.ProductDto;
import com.iluwatar.datatransfer.product.ProductResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    // Example 1: Customer DTO
    var customerOne = new CustomerDto("1", "Kelly", "Brown");
    var customerTwo = new CustomerDto("2", "Alfonso", "Bass");
    var customers = new ArrayList<>(List.of(customerOne, customerTwo));

    var customerResource = new CustomerResource(customers);

    LOGGER.info("All customers:");
    var allCustomers = customerResource.customers();
    printCustomerDetails(allCustomers);

    LOGGER.info("----------------------------------------------------------");

    LOGGER.info("Deleting customer with id {1}");
    customerResource.delete(customerOne.id());
    allCustomers = customerResource.customers();
    printCustomerDetails(allCustomers);

    LOGGER.info("----------------------------------------------------------");

    LOGGER.info("Adding customer three}");
    var customerThree = new CustomerDto("3", "Lynda", "Blair");
    customerResource.save(customerThree);
    allCustomers = customerResource.customers();
    printCustomerDetails(allCustomers);

    // Example 2: Product DTO

    Product tv =
        Product.builder().id(1L).name("TV").supplier("Sony").price(1000D).cost(1090D).build();
    Product microwave =
        Product.builder()
            .id(2L)
            .name("microwave")
            .supplier("Delonghi")
            .price(1000D)
            .cost(1090D)
            .build();
    Product refrigerator =
        Product.builder()
            .id(3L)
            .name("refrigerator")
            .supplier("Botsch")
            .price(1000D)
            .cost(1090D)
            .build();
    Product airConditioner =
        Product.builder()
            .id(4L)
            .name("airConditioner")
            .supplier("LG")
            .price(1000D)
            .cost(1090D)
            .build();
    List<Product> products =
        new ArrayList<>(Arrays.asList(tv, microwave, refrigerator, airConditioner));
    ProductResource productResource = new ProductResource(products);

    LOGGER.info(
        "####### List of products including sensitive data just for admins: \n {}",
        Arrays.toString(productResource.getAllProductsForAdmin().toArray()));
    LOGGER.info(
        "####### List of products for customers: \n {}",
        Arrays.toString(productResource.getAllProductsForCustomer().toArray()));

    LOGGER.info("####### Going to save Sony PS5 ...");
    ProductDto.Request.Create createProductRequestDto =
        new ProductDto.Request.Create()
            .setName("PS5")
            .setCost(1000D)
            .setPrice(1220D)
            .setSupplier("Sony");
    productResource.save(createProductRequestDto);
    LOGGER.info(
        "####### List of products after adding PS5: {}",
        Arrays.toString(productResource.products().toArray()));
  }

  private static void printCustomerDetails(List<CustomerDto> allCustomers) {
    allCustomers.forEach(customer -> LOGGER.info(customer.firstName()));
  }
}
