package com.fys.springstarter;

import lombok.Setter;

@Setter
public class HelloService {

  private String msg;
  private boolean show = true;

  public String sayHello() {
    return show ? "Hello." + msg : "Hidden";
  }

}
