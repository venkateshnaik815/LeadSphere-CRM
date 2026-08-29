package com.leadsphere.crm.patterns;

public class StubSentimentAnalysisServer implements SentimentAnalysisServer {

  @Override
  public String analyzeSentiment(String text) {
    if (text.toLowerCase().contains("good")) {
      return "Positive";
    } else if (text.toLowerCase().contains("bad")) {
      return "Negative";
    } else {
      return "Neutral";
    }
  }
}
