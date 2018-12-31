package com.fys.hystrix.example;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

public class HelloWorldCommand extends HystrixCommand<String> {

  private final String name;
  public HelloWorldCommand(String name) {
    super(Setter.withGroupKey(
        HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
        .andCommandPropertiesDefaults(
            HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
    this.name = name;
  }

  @Override
  protected String run() throws Exception {
    TimeUnit.MILLISECONDS.sleep(1000);
    return "Hello " + name + " Thread: " + Thread.currentThread().getName();
  }

  @Override
  protected String getFallback() {
    return "Execute Fallback";
  }

  public static void main(String[] args) throws Exception {

    /*HelloWorldCommand hwCommand = new HelloWorldCommand("Synchronous-hystrix");
    String result = hwCommand.execute();
    System.out.println("Result: " + result);

    hwCommand = new HelloWorldCommand("Asynchronous-hystrix");
    Future<String> future = hwCommand.queue();
    result = future.get(100, TimeUnit.MILLISECONDS);
    System.out.println("Result: "+ result);

    System.out.println("MainThread="+Thread.currentThread().getName());*/

    HelloWorldCommand command = new HelloWorldCommand("test-Fallback");
    String result = command.execute();
    System.out.println("Result: "+ result);

  }
}
