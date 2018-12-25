package com.fys.springboot.aop;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@RestController
public class IndexController {


  @GetMapping({"", ""})
  public String index() {
    return "index";
  }

  @GetMapping({"/test"})
  public Map test(@RequestParam String name) {
    ConcurrentMap<String, Object> ret = Maps.newConcurrentMap();
    ret.put("name", name);
    return ret;
  }

}
