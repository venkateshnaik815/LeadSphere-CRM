package com.leadsphere.crm.patterns;

import java.util.Random;
import java.util.function.Supplier;

public class RealSentimentAnalysisServer implements SentimentAnalysisServer {
  private final Supplier<Integer> sentimentSupplier;

  // Constructor
  public RealSentimentAnalysisServer(Supplier<Integer> sentimentSupplier) {
    this.sentimentSupplier = sentimentSupplier;
  }

  @SuppressWarnings("java:S2245") // Safe use: Randomness is for simulation/testing only
  public RealSentimentAnalysisServer() {
    this(() -> new Random().nextInt(3));
  }

  @Override
  public String analyzeSentiment(String text) {
    int sentiment = sentimentSupplier.get();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return switch (sentiment) {
      case 0 -> "Positive";
      case 1 -> "Negative";
      default -> "Neutral";
    };
  }
}
