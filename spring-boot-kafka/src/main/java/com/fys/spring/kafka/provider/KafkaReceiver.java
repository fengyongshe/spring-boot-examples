package com.fys.spring.kafka.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {

  @KafkaListener(topics = {"foo"})
  public void listen(String message) {
    System.out.println("Foo Topic Receive " + message);
  }

}
