package dev.kwanghui.rabbitmqdemo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import dev.kwanghui.rabbitmqdemo.dto.Product;
import dev.kwanghui.rabbitmqdemo.dto.User;

@Service
public class RabbitMQJsonConsumer {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

  @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
  public void consumeJsonMessage(User user) {
    LOGGER.info(String.format("Received user JSON message -> %s", user.toString()));
  }

  @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
  public void consumeJsonMessage(Product product) {
    LOGGER.info(String.format("Received product JSON message -> %s", product.toString()));
  }
}
