package com.fys.cqrs.handler;

import com.fys.cqrs.event.ToDoItemCompletedEvent;
import com.fys.cqrs.event.ToDoItemCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;

public class ToDoEventHandler {

  @EventHandler
  public void handle(ToDoItemCreatedEvent event) {
    System.out.println("We've got something to do :" + event.getDescription()
                                             + " ( " + event.getTodoId() +" )" );
  }

  @EventHandler
  public void handle(ToDoItemCompletedEvent event) {
    System.out.println("We've complted a task:" + event.getTodoId());
  }

}
