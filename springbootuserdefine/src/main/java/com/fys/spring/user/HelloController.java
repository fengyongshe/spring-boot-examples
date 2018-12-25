package com.fys.spring.user;

import com.fys.springboot.starter.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  private DemoService demoService;

  @RequestMapping("/hello")
  public String sayHello() {
    return demoService.userInfo();
  }

}
