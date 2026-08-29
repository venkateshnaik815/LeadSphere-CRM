package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {
  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  public static final String NOT_FOUND = "not found";
  private static BusinessOperation<String> op;

  public static void main(String[] args) throws Exception {
    noErrors();
    errorNoRetry();
    errorWithRetry();
    errorWithRetryExponentialBackoff();
  }

  private static void noErrors() throws Exception {
    op = new FindCustomer("123");
    op.perform();
    LOG.info("Sometimes the operation executes with no errors.");
  }

  private static void errorNoRetry() throws Exception {
    op = new FindCustomer("123", new CustomerNotFoundException(NOT_FOUND));
    try {
      op.perform();
    } catch (CustomerNotFoundException e) {
      LOG.info("Yet the operation will throw an error every once in a while.");
    }
  }

  private static void errorWithRetry() throws Exception {
    final var retry =
        new Retry<>(
            new FindCustomer("123", new CustomerNotFoundException(NOT_FOUND)),
            3, // 3 attempts
            100, // 100 ms delay between attempts
            e -> CustomerNotFoundException.class.isAssignableFrom(e.getClass()));
    op = retry;
    final var customerId = op.perform();
    LOG.info(
        String.format(
            "However, retrying the operation while ignoring a recoverable error will eventually yield "
                + "the result %s after a number of attempts %s",
            customerId, retry.attempts()));
  }

  private static void errorWithRetryExponentialBackoff() throws Exception {
    final var retry =
        new RetryExponentialBackoff<>(
            new FindCustomer("123", new CustomerNotFoundException(NOT_FOUND)),
            6, // 6 attempts
            30000, // 30 s max delay between attempts
            e -> CustomerNotFoundException.class.isAssignableFrom(e.getClass()));
    op = retry;
    final var customerId = op.perform();
    LOG.info(
        String.format(
            "However, retrying the operation while ignoring a recoverable error will eventually yield "
                + "the result %s after a number of attempts %s",
            customerId, retry.attempts()));
  }
}
