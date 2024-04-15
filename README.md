# RabbitMQ 실행
```bash
docker compose up -d
```

# RabbitMQ 관리자 포탈 접속
http://localhost:15672

# 소스 설명
## Spring RabbitMQ 라이브러리 추가 ([build.gradle](https://github.com/badanol/RabbitMQDemo/blob/main/build.gradle))
```bash
  implementation 'org.springframework.boot:spring-boot-starter-amqp'
```

## RabbitMQ Configuration ([RabbitMQConfig.java](https://github.com/badanol/RabbitMQDemo/blob/main/src/main/java/dev/kwanghui/rabbitmqdemo/config/RabbitMQConfig.java))
Cousumer만 구성시에는 다음 설정만 필요함

```java
  // spring bean for queue (store json message)
  @Bean
  public Queue jsonQueue() {
    return new Queue(jsonQueue);
  }  

  // JSON 형식으로 전달되는 메시지 내용을 DTO로 자동 변환하기 위해 MessageConvert 설정
  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }
```

## Consumer ([RabbitMQJsonConsumer.java](https://github.com/badanol/RabbitMQDemo/blob/main/src/main/java/dev/kwanghui/rabbitmqdemo/consumer/RabbitMQJsonConsumer.java))
동일한 큐에서 전달되는 다른 DTO에 각각 @RabbitListener를 설정하여 수신 가능

```java
  @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
  public void consumeJsonMessage(User user) {
    LOGGER.info(String.format("Received user JSON message -> %s", user.toString()));
  }

  @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
  public void consumeJsonMessage(Product product) {
    LOGGER.info(String.format("Received product JSON message -> %s", product.toString()));
  }
```

## 설정 파일 ([application.properties](https://github.com/badanol/RabbitMQDemo/blob/main/src/main/resources/application.properties))
rabbitmq 설정값들을 기록하며 실제 사용하는 큐 정보도 설정 파일에서 주입
