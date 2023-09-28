package com.example.lmxdemo.service;

import com.example.statemachinedemo.domain.Fault;
import com.example.statemachinedemo.statemachine.enums.Event;
import com.example.statemachinedemo.statemachine.enums.State;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    public StateMachineService stateMachineService;

    private Fault fault = new Fault();

    public void init() {
        this.fault.setState(State.CREATE.getValue());
    }

    public boolean transform(int eventValue) {
        Event event = Event.fromValue(eventValue);
        boolean res;
        try {
            res = stateMachineService.sendEvent(event, fault);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

}
