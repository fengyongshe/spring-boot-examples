package com.fys.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.fys.zipkin")
public class FooApplication {

  public static void main(String[] args) {
    SpringApplication.run(FooApplication.class);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
