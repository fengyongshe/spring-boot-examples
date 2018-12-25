package com.fys.spring.kafka;

import com.fys.spring.kafka.consumer.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class KafkaApplication {

  public static void main(String[] args) throws Exception {
    log.info("Start KafkaApp");
    ConfigurableApplicationContext context =
        SpringApplication.run(KafkaApplication.class, args);
    log.info("Get Sender");
    KafkaSender sender = context.getBean(KafkaSender.class);
    log.info("Send A message");
    sender.send();
  }
}
