package com.example.lmxdemo.statemachine.config;


import com.example.statemachinedemo.domain.Fault;
import com.example.statemachinedemo.statemachine.enums.Event;
import com.example.statemachinedemo.statemachine.enums.State;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.util.Objects;

@Configuration
@WithStateMachine
public class EventConfig {

    @OnTransition(source = "READY", target = "DRILLING")
    public boolean start(Message<Event> message) {
        Fault fault = (Fault) message.getHeaders().get("failure");
        if (Objects.isNull(fault)) {
            System.out.println("[READY -> DRILLING]: obj is null");
            return false;
        }
        fault.setState(State.DRILLING.getValue());
        System.out.println("READY -> DRILLING");
        return true;
    }

    @OnTransition(source = "DRILLING", target = "PAUSE")
    public void pause() {
        System.out.println("DRILLING -> PAUSE");
    }

    @OnTransition(source = "PAUSE", target = "DRILLING")
    public void goon() {
        System.out.println("PAUSE -> DRILLING");
    }

    @OnTransition(source = "DRILLING", target = "SUCCESS")
    public void complete() {
        System.out.println("DRILLING -> SUCCESS");
    }

    @OnTransition(source = "DRILLING", target = "FAIL")
    public void drillerror() {
        System.out.println("DRILLING -> FAIL");
    }

    @OnTransition(source = "SUCCESS", target = "READY")
    public void restart() {
        System.out.println("SUCCESS -> READY");
    }

    @OnTransition(source = "READY", target = "FAIL")
    public void conferror() {
        System.out.println("READY -> FAIL");
    }


}
