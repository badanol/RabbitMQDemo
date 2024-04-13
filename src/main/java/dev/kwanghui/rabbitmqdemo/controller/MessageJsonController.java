package dev.kwanghui.rabbitmqdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kwanghui.rabbitmqdemo.dto.Product;
import dev.kwanghui.rabbitmqdemo.dto.User;
import dev.kwanghui.rabbitmqdemo.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
  
  private final RabbitMQJsonProducer jsonProducer;

  public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
    this.jsonProducer = jsonProducer;
  }

  @PostMapping("/publish")
  public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
    jsonProducer.sendJsonMessage(user);
    return ResponseEntity.ok("Json message sent to RabbitMQ ...");
  }

  @PostMapping("/publish-product")
  public ResponseEntity<String> sendJsonMessage(@RequestBody Product product) {
    jsonProducer.sendJsonMessage(product);
    return ResponseEntity.ok("Json message sent to RabbitMQ ...");
  }
}
