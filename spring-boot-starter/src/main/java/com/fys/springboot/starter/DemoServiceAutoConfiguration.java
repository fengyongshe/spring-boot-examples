package com.fys.springboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@ConditionalOnClass(DemoService.class)
@ConditionalOnProperty(prefix = "demo", value = "enabled", matchIfMissing = true)
public class DemoServiceAutoConfiguration {

  @Autowired
  private DemoProperties demoProperties;

  @Bean
  @ConditionalOnMissingBean(DemoService.class)
  public DemoService demoService() {
    System.out.println("Create DemoService, for demoProperties:" + demoProperties.toString());
    DemoService demoService = new DemoService(demoProperties);
    return demoService;
  }

}
