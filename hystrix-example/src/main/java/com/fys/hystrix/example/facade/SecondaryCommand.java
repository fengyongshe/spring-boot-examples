package com.fys.hystrix.example.facade;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class SecondaryCommand extends HystrixCommand<String> {

  public final int id;
  public SecondaryCommand(int id) {
    super(
        Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("SecondaryCommand"))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SecondaryCommand"))
            .andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(100)
            )
    );
    this.id = id;
  }

  @Override
  protected String run() {
    return "responseFromSecondary-" + id;
  }

}
