package com.fys.springboot.starter;

public class DemoService {

  private DemoProperties demoProperties;

  public DemoService(DemoProperties demoProperties) {
    this.demoProperties = demoProperties;
  }

  public String userInfo() {
    return String.format("User[id=%s, name=%s, age=%s, email=%s]",
        demoProperties.getId(), demoProperties.getName(), demoProperties.getAge(), demoProperties.getEmail());
  }

}
