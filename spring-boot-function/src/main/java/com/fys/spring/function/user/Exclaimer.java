package com.fys.spring.function.user;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Exclaimer implements Function<Flux<String>,Flux<String>> {

  @Override
  public Flux<String> apply(Flux<String> words) {
    return words.map(word -> word+"!!!");
  }

}
