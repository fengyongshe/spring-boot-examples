package com.fys.cqrs;

import com.fys.cqrs.cmd.CreateToDoItemCommand;
import com.fys.cqrs.cmd.MarkCompletedCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class ToDoItemRunner {

  private CommandGateway commandGateway;

  public ToDoItemRunner(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  public void run() {
    final String itemId = UUID.randomUUID().toString();

    commandGateway.send(new CreateToDoItemCommand(itemId, "Need to do this"));
    commandGateway.send(new MarkCompletedCommand(itemId));
  }

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ToDoItemRunner runner = new ToDoItemRunner(context.getBean(CommandGateway.class));
    runner.run();
  }
}
