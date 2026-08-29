package com.leadsphere.crm.patterns;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaMessageConsumer implements AutoCloseable, Runnable {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageConsumer.class);
  private final Consumer<String, String> consumer;
  private final ObjectMapper objectMapper;
  private final String topic;
  private final java.util.function.Consumer<Message> messageHandler;
  private final AtomicBoolean running = new AtomicBoolean(true);

  public KafkaMessageConsumer(
      String bootstrapServers,
      String groupId,
      String topic,
      java.util.function.Consumer<Message> messageHandler) {
    this(createDefaultConsumer(bootstrapServers, groupId), topic, messageHandler);
  }

  KafkaMessageConsumer(
      Consumer<String, String> consumer,
      String topic,
      java.util.function.Consumer<Message> messageHandler) {
    this.consumer = consumer;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
    this.topic = topic;
    this.messageHandler = messageHandler;
  }

  private static Consumer<String, String> createDefaultConsumer(
      String bootstrapServers, String groupId) {
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
    return new KafkaConsumer<>(props);
  }

  @Override
  public void run() {
    try {
      consumer.subscribe(Collections.singletonList(topic));
      LOGGER.info("Consumer subscribed to topic: {}", topic);

      while (running.get()) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        records.forEach(
            record -> {
              try {
                Message message = objectMapper.readValue(record.value(), Message.class);
                LOGGER.info("Received message from topic '{}': {}", topic, message.getId());
                messageHandler.accept(message);
              } catch (Exception e) {
                LOGGER.error("Error processing message: {}", e.getMessage(), e);
              }
            });
      }
    } catch (Exception e) {
      LOGGER.error("Consumer error: {}", e.getMessage(), e);
    } finally {
      consumer.close();
      LOGGER.info("Consumer closed for topic: {}", topic);
    }
  }

  public void stop() {
    running.set(false);
  }

  @Override
  public void close() {
    stop();
  }
}
