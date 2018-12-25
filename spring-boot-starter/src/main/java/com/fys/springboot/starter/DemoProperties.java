package com.fys.springboot.starter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "demo")
@Setter
@Getter
@ToString
public class DemoProperties {

  private String id;
  private String name;
  private int age;
  private String email;

}
