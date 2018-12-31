package com.fys.hystrix.example.facade;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandFacadeWithPrimarySecondary extends HystrixCommand<String> {

  private final static DynamicBooleanProperty usePrimary =
      DynamicPropertyFactory.getInstance().getBooleanProperty("primarySecondary.usePrimary", true);
  private final int id;
  public CommandFacadeWithPrimarySecondary(int id) {
    super(Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("PrimarySecondaryCommand"))
        .andCommandPropertiesDefaults(
            HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
    this.id = id;
  }

  @Override
  protected String run() {
    if(usePrimary.get()) {
      return new PrimaryCommand(id).execute();
    } else {
      return new SecondaryCommand(id).execute();
    }
  }
}
