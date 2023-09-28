package com.example.lmxdemo.service;

import com.example.statemachinedemo.domain.Fault;
import com.example.statemachinedemo.statemachine.enums.Event;
import com.example.statemachinedemo.statemachine.enums.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Service
public class StateMachineService {

    @Qualifier("FaultStateMachineFactory")
    @Autowired
    private StateMachineFactory<State, Event> stateMachineFactory;

    @Qualifier("faultStateMachinePersister")
    @Autowired
    public StateMachinePersister<State, Event, Fault> persister;

    public boolean sendEvent(Event event, Fault fault) {
        boolean result;
        StateMachine<State, Event> stateMachine = this.stateMachineFactory.getStateMachine();
        try {
            stateMachine.start();
            this.persister.restore(stateMachine, fault);
            result = stateMachine.sendEvent(MessageBuilder.withPayload(event).setHeader("fault", fault).build());
            System.out.println(result);
            if (result) {
                persister.persist(stateMachine, fault);
            } else {
                throw new RuntimeException("状态机转换失败，当前状态是：" + State.fromValue(fault.getState()).getDesc());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            stateMachine.stop();
        }
        return result;
    }

}
