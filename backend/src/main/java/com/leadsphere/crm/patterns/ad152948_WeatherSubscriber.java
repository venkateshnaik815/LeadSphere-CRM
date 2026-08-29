package com.leadsphere.crm.patterns;

import com.iluwatar.publish.subscribe.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class WeatherSubscriber implements Subscriber {

  private static final Logger logger = LoggerFactory.getLogger(WeatherSubscriber.class);

  @Override
  public void onMessage(Message message) {
    if (message.content() instanceof String content) {
      logger.info("Weather Subscriber: {} issued message: {}", this.hashCode(), content);
    } else {
      logger.error(
          "Unknown content type: {} expected: {}",
          message.content().getClass().getSimpleName(),
          String.class.getSimpleName());
    }
  }
}
