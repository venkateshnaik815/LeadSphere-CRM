package com.leadsphere.crm.patterns;

public class DefaultCircuitBreaker implements CircuitBreaker {

  private final long timeout;
  private final long retryTimePeriod;
  private final RemoteService service;
  long lastFailureTime;
  private String lastFailureResponse;
  int failureCount;
  private final int failureThreshold;
  private State state;
  // Future time offset, in nanoseconds
  private final long futureTime = 1_000_000_000_000L;

  DefaultCircuitBreaker(
      RemoteService serviceToCall, long timeout, int failureThreshold, long retryTimePeriod) {
    this.service = serviceToCall;
    // We start in a closed state hoping that everything is fine
    this.state = State.CLOSED;
    this.failureThreshold = failureThreshold;
    // Timeout for the API request.
    // Used to break the calls made to remote resource if it exceeds the limit
    this.timeout = timeout;
    this.retryTimePeriod = retryTimePeriod;
    // An absurd amount of time in future which basically indicates the last failure never happened
    this.lastFailureTime = System.nanoTime() + futureTime;
    this.failureCount = 0;
  }

  // Reset everything to defaults
  @Override
  public void recordSuccess() {
    this.failureCount = 0;
    this.lastFailureTime = System.nanoTime() + futureTime;
    this.state = State.CLOSED;
  }

  @Override
  public void recordFailure(String response) {
    failureCount = failureCount + 1;
    this.lastFailureTime = System.nanoTime();
    // Cache the failure response for returning on open state
    this.lastFailureResponse = response;
  }

  // Evaluate the current state based on failureThreshold, failureCount and lastFailureTime.
  protected void evaluateState() {
    if (failureCount >= failureThreshold) { // Then something is wrong with remote service
      if ((System.nanoTime() - lastFailureTime) > retryTimePeriod) {
        // We have waited long enough and should try checking if service is up
        state = State.HALF_OPEN;
      } else {
        // Service would still probably be down
        state = State.OPEN;
      }
    } else {
      // Everything is working fine
      state = State.CLOSED;
    }
  }

  @Override
  public String getState() {
    evaluateState();
    return state.name();
  }

  @Override
  public void setState(State state) {
    this.state = state;
    switch (state) {
      case OPEN -> {
        this.failureCount = failureThreshold;
        this.lastFailureTime = System.nanoTime();
      }
      case HALF_OPEN -> {
        this.failureCount = failureThreshold;
        this.lastFailureTime = System.nanoTime() - retryTimePeriod;
      }
      default -> this.failureCount = 0;
    }
  }

  @Override
  public String attemptRequest() throws RemoteServiceException {
    evaluateState();
    if (state == State.OPEN) {
      // return cached response if the circuit is in OPEN state
      return this.lastFailureResponse;
    } else {
      // Make the API request if the circuit is not OPEN
      try {
        // In a real application, this would be run in a thread and the timeout
        // parameter of the circuit breaker would be utilized to know if service
        // is working. Here, we simulate that based on server response itself
        var response = service.call();
        // Yay!! the API responded fine. Let's reset everything.
        recordSuccess();
        return response;
      } catch (RemoteServiceException ex) {
        recordFailure(ex.getMessage());
        throw ex;
      }
    }
  }
}
