package com.fys.springstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(
    prefix = "hello",
    value = "enabled",
    matchIfMissing = true
)
public class HelloAutoConfiguration {

  @Autowired
  private HelloProperties helloProperties;

  @Bean
  @ConditionalOnMissingBean(HelloService.class)
  public HelloService helloService() {
    System.out.println(">>>> The HelloService Not Found, Execute Create New Bean.");
    HelloService helloService = new HelloService();
    helloService.setMsg(helloProperties.getMsg());
    helloService.setShow(helloProperties.isShow());
    return helloService;
  }

}
