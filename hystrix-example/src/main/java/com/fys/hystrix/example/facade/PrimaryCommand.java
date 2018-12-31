package com.fys.hystrix.example.facade;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class PrimaryCommand extends HystrixCommand<String> {

  public final int id;
  public PrimaryCommand(int id) {
    super(
        Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand"))
        .andCommandPropertiesDefaults(
            HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(600)
        )
    );
    this.id = id;
  }

  @Override
  protected String run() {
    return "responseFromPrimary-" + id;
  }

}
