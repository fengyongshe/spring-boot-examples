package com.fys.springstarter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

  private String msg = "Hello FYS";
  private boolean show = true;

}
