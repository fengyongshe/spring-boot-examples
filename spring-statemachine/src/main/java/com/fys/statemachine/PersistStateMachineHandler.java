package com.fys.statemachine;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.listener.AbstractCompositeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;

public class PersistStateMachineHandler extends LifecycleObjectSupport {

  private final StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine ;
  private final PersistingStateChangeInterceptor interceptor = new PersistingStateChangeInterceptor();
  private final CompositePersistStateChangeListener listeners = new CompositePersistStateChangeListener();

  public PersistStateMachineHandler(StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine) {
    Assert.notNull(stateMachine, "State machine must be set");
    this.stateMachine = stateMachine;
  }

  @Override
  protected void onInit() throws Exception {
    stateMachine.getStateMachineAccessor()
                .doWithAllRegions(function -> function.addStateMachineInterceptor(interceptor));
  }

  public boolean handleEventWithState(Message<OrderStatusChangeEvent> event, OrderStatus state) {
    stateMachine.stop();
    List<StateMachineAccess<OrderStatus,OrderStatusChangeEvent>> withAllRegions
        = stateMachine.getStateMachineAccessor().withAllRegions();
    for(StateMachineAccess<OrderStatus,OrderStatusChangeEvent> a : withAllRegions) {
      a.resetStateMachine(new DefaultStateMachineContext<>(state,null,null,null));
    }
    stateMachine.start();
    return stateMachine.sendEvent(event);
  }

  public void addPersistStateChangeListener(PersistStateChangeListener listener) {
    this.listeners.register(listener);
  }

  public interface PersistStateChangeListener {

    /**
     * 当状态被持久化，调用此方法
     *
     * @param state
     * @param message
     * @param transition
     * @param stateMachine 状态机实例
     */
    void onPersist(State<OrderStatus, OrderStatusChangeEvent> state,
                   Message<OrderStatusChangeEvent> message,
                   Transition<OrderStatus, OrderStatusChangeEvent> transition,
                   StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine);
  }

  private class PersistingStateChangeInterceptor
        extends StateMachineInterceptorAdapter<OrderStatus, OrderStatusChangeEvent> {

    @Override
    public void preStateChange(State<OrderStatus, OrderStatusChangeEvent> state,
                               Message<OrderStatusChangeEvent> message,
                               Transition<OrderStatus, OrderStatusChangeEvent> transition,
                               StateMachine<OrderStatus,OrderStatusChangeEvent> stateMachine) {
      listeners.onPersist(state,message,transition,stateMachine);
    }
  }


  private class CompositePersistStateChangeListener
            extends AbstractCompositeListener<PersistStateChangeListener>
            implements PersistStateChangeListener {

    @Override
    public void onPersist( State<OrderStatus, OrderStatusChangeEvent> state,
                    Message<OrderStatusChangeEvent> message,
                    Transition<OrderStatus, OrderStatusChangeEvent> transition,
                    StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine){
      for(Iterator<PersistStateChangeListener> iterator = getListeners().reverse();iterator.hasNext();) {
        PersistStateChangeListener listener = iterator.next();
        listener.onPersist(state,message,transition,stateMachine);
      }
    }
  }



}
