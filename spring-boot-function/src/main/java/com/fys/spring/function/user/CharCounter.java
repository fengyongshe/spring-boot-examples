package com.fys.spring.function.user;

import java.util.function.Function;

public class CharCounter implements Function<String,Integer> {

  @Override
  public Integer apply(String word) {
    return word.length();
  }

}
