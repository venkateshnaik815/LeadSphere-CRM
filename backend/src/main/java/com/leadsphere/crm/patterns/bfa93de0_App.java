package com.leadsphere.crm.patterns;

import com.iluwatar.publish.subscribe.model.Message;
import com.iluwatar.publish.subscribe.model.Topic;
import com.iluwatar.publish.subscribe.publisher.Publisher;
import com.iluwatar.publish.subscribe.publisher.PublisherImpl;
import com.iluwatar.publish.subscribe.subscriber.CustomerSupportSubscriber;
import com.iluwatar.publish.subscribe.subscriber.DelayedWeatherSubscriber;
import com.iluwatar.publish.subscribe.subscriber.Subscriber;
import com.iluwatar.publish.subscribe.subscriber.WeatherSubscriber;
import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) throws InterruptedException {

    final String topicWeather = "WEATHER";
    final String topicTemperature = "TEMPERATURE";
    final String topicCustomerSupport = "CUSTOMER_SUPPORT";

    // 1. create the publisher.
    Publisher publisher = new PublisherImpl();

    // 2. define the topics and register on publisher
    Topic weatherTopic = new Topic(topicWeather);
    publisher.registerTopic(weatherTopic);

    Topic temperatureTopic = new Topic(topicTemperature);
    publisher.registerTopic(temperatureTopic);

    Topic supportTopic = new Topic(topicCustomerSupport);
    publisher.registerTopic(supportTopic);

    // 3. Create the subscribers and subscribe to the relevant topics
    // weatherSub1 will subscribe to two topics WEATHER and TEMPERATURE.
    Subscriber weatherSub1 = new WeatherSubscriber();
    weatherTopic.addSubscriber(weatherSub1);
    temperatureTopic.addSubscriber(weatherSub1);

    // weatherSub2 will subscribe to WEATHER topic
    Subscriber weatherSub2 = new WeatherSubscriber();
    weatherTopic.addSubscriber(weatherSub2);

    // delayedWeatherSub will subscribe to WEATHER topic
    // NOTE :: DelayedWeatherSubscriber has a 0.2 sec delay of processing message.
    Subscriber delayedWeatherSub = new DelayedWeatherSubscriber();
    weatherTopic.addSubscriber(delayedWeatherSub);

    // subscribe the customer support subscribers to the CUSTOMER_SUPPORT topic.
    Subscriber supportSub1 = new CustomerSupportSubscriber();
    supportTopic.addSubscriber(supportSub1);
    Subscriber supportSub2 = new CustomerSupportSubscriber();
    supportTopic.addSubscriber(supportSub2);

    // 4. publish message from each topic
    publisher.publish(weatherTopic, new Message("earthquake"));
    publisher.publish(temperatureTopic, new Message("23C"));
    publisher.publish(supportTopic, new Message("support@test.de"));

    // 5. unregister subscriber from TEMPERATURE topic
    temperatureTopic.removeSubscriber(weatherSub1);

    // 6. publish message under TEMPERATURE topic
    publisher.publish(temperatureTopic, new Message("0C"));

    TimeUnit.SECONDS.sleep(2);
  }
}
