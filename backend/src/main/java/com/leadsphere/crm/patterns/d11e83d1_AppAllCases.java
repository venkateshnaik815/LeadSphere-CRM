package com.leadsphere.crm.patterns;

import com.iluwatar.commander.employeehandle.EmployeeDatabase;
import com.iluwatar.commander.employeehandle.EmployeeHandle;
import com.iluwatar.commander.exceptions.DatabaseUnavailableException;
import com.iluwatar.commander.exceptions.ItemUnavailableException;
import com.iluwatar.commander.exceptions.PaymentDetailsErrorException;
import com.iluwatar.commander.messagingservice.MessagingDatabase;
import com.iluwatar.commander.messagingservice.MessagingService;
import com.iluwatar.commander.paymentservice.PaymentDatabase;
import com.iluwatar.commander.paymentservice.PaymentService;
import com.iluwatar.commander.queue.QueueDatabase;
import com.iluwatar.commander.shippingservice.ShippingDatabase;
import com.iluwatar.commander.shippingservice.ShippingService;

public class AppAllCases {
  private static final RetryParams retryParams = RetryParams.DEFAULT;
  private static final TimeLimits timeLimits = TimeLimits.DEFAULT;

  // Employee Database Fail Case
  void employeeDatabaseUnavailableCase() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms = new MessagingService(new MessagingDatabase());
    var eh =
        new EmployeeHandle(
            new EmployeeDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var qdb =
        new QueueDatabase(
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Employee Database Success Case
  void employeeDbSuccessCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase(), new ItemUnavailableException());
    var ms = new MessagingService(new MessagingDatabase());
    var eh =
        new EmployeeHandle(
            new EmployeeDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Messaging Database Fail Cases
  void messagingDatabaseUnavailableCasePaymentSuccess() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase());
    var ms =
        new MessagingService(
            new MessagingDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  void messagingDatabaseUnavailableCasePaymentError() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms =
        new MessagingService(
            new MessagingDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  void messagingDatabaseUnavailableCasePaymentFailure() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms =
        new MessagingService(
            new MessagingDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb =
        new QueueDatabase(
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Messaging Database Success Case
  void messagingSuccessCase() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms = new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Payment Database Fail Cases
  void paymentNotPossibleCase() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new PaymentDetailsErrorException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms = new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase(new DatabaseUnavailableException());
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  void paymentDatabaseUnavailableCase() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms = new MessagingService(new MessagingDatabase());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Payment Database Success Case
  void paymentSuccessCase() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms = new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase(new DatabaseUnavailableException());
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Queue Database Fail Cases
  void queuePaymentTaskDatabaseUnavailableCase() {
    var ps =
        new PaymentService(
            new PaymentDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ss = new ShippingService(new ShippingDatabase());
    var ms = new MessagingService(new MessagingDatabase());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb =
        new QueueDatabase(
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  void queueMessageTaskDatabaseUnavailableCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase());
    var ms =
        new MessagingService(
            new MessagingDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb =
        new QueueDatabase(
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  void queueEmployeeDbTaskDatabaseUnavailableCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase(), new ItemUnavailableException());
    var ms = new MessagingService(new MessagingDatabase());
    var eh =
        new EmployeeHandle(
            new EmployeeDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var qdb =
        new QueueDatabase(
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Queue Database Success Cases
  void queueSuccessCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase(), new ItemUnavailableException());
    var ms = new MessagingService(new MessagingDatabase());
    var eh =
        new EmployeeHandle(
            new EmployeeDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Shipping Database Fail Cases
  void itemUnavailableCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase(), new ItemUnavailableException());
    var ms = new MessagingService(new MessagingDatabase());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  void shippingDatabaseUnavailableCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss =
        new ShippingService(
            new ShippingDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var ms = new MessagingService(new MessagingDatabase());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  void shippingItemNotPossibleCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase(), new ItemUnavailableException());
    var ms = new MessagingService(new MessagingDatabase());
    var eh = new EmployeeHandle(new EmployeeDatabase());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  // Shipping Database Success Cases
  void shippingSuccessCase() {
    var ps = new PaymentService(new PaymentDatabase());
    var ss = new ShippingService(new ShippingDatabase(), new ItemUnavailableException());
    var ms = new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException());
    var eh =
        new EmployeeHandle(
            new EmployeeDatabase(),
            new DatabaseUnavailableException(),
            new DatabaseUnavailableException());
    var qdb = new QueueDatabase();
    var c = new Commander(eh, ps, ss, ms, qdb, retryParams, timeLimits);
    var user = new User("Jim", "ABCD");
    var order = new Order(user, "book", 10f);
    c.placeOrder(order);
  }

  public static void main(String[] args) {
    AppAllCases app = new AppAllCases();

    // Employee Database cases
    app.employeeDatabaseUnavailableCase();
    app.employeeDbSuccessCase();

    // Messaging Database cases
    app.messagingDatabaseUnavailableCasePaymentSuccess();
    app.messagingDatabaseUnavailableCasePaymentError();
    app.messagingDatabaseUnavailableCasePaymentFailure();
    app.messagingSuccessCase();

    // Payment Database cases
    app.paymentNotPossibleCase();
    app.paymentDatabaseUnavailableCase();
    app.paymentSuccessCase();

    // Queue Database cases
    app.queuePaymentTaskDatabaseUnavailableCase();
    app.queueMessageTaskDatabaseUnavailableCase();
    app.queueEmployeeDbTaskDatabaseUnavailableCase();
    app.queueSuccessCase();

    // Shipping Database cases
    app.itemUnavailableCase();
    app.shippingDatabaseUnavailableCase();
    app.shippingItemNotPossibleCase();
    app.shippingSuccessCase();
  }
}
