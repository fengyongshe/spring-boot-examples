package com.fys.cqrs.model;

import com.fys.cqrs.cmd.CreateToDoItemCommand;
import com.fys.cqrs.cmd.MarkCompletedCommand;
import com.fys.cqrs.event.ToDoItemCompletedEvent;
import com.fys.cqrs.event.ToDoItemCreatedEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

public class ToDoItem extends AbstractAnnotatedAggregateRoot {

  @AggregateIdentifier
  private String id;

  public ToDoItem() {
  }

  @CommandHandler
  public ToDoItem(CreateToDoItemCommand command) {
    apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
  }

  @EventHandler
  public void on(ToDoItemCreatedEvent event) {
    this.id = event.getTodoId();
  }

  @CommandHandler
  public void markCompleted(MarkCompletedCommand command) {
    apply(new ToDoItemCompletedEvent(id));
  }
}
