package dev.kwanghui.rabbitmqdemo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.kwanghui.rabbitmqdemo.dto.Product;
import dev.kwanghui.rabbitmqdemo.dto.User;

@Service
public class RabbitMQJsonProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
  
  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.json.key}")
  private String routingJsonKey;

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendJsonMessage(User user) {
    LOGGER.info(String.format("Json message sent -> %s", user.toString()));
    rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
  }

  public void sendJsonMessage(Product product) {
    LOGGER.info(String.format("Json message sent -> %s", product.toString()));
    rabbitTemplate.convertAndSend(exchange, routingJsonKey, product);
  }
}
