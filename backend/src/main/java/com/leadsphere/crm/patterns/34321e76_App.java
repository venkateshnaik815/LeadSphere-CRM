package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static void main(String[] args) {
    LOGGER.info("Setting up the real sentiment analysis server.");
    RealSentimentAnalysisServer realSentimentAnalysisServer = new RealSentimentAnalysisServer();
    String text = "This movie is soso";
    LOGGER.info("Analyzing input: {}", text);
    String sentiment = realSentimentAnalysisServer.analyzeSentiment(text);
    LOGGER.info("The sentiment is: {}", sentiment);

    LOGGER.info("Setting up the stub sentiment analysis server.");
    StubSentimentAnalysisServer stubSentimentAnalysisServer = new StubSentimentAnalysisServer();
    text = "This movie is so bad";
    LOGGER.info("Analyzing input: {}", text);
    sentiment = stubSentimentAnalysisServer.analyzeSentiment(text);
    LOGGER.info("The sentiment is: {}", sentiment);
  }
}
