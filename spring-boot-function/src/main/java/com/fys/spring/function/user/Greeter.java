package com.fys.spring.function.user;

import java.util.function.Function;

public class Greeter implements Function<String,String> {

  @Override
  public String apply(String name) {
    return "Hello " + name;
  }

}
