package com.example.lmxdemo.statemachine.config;


import com.example.statemachinedemo.domain.Fault;
import com.example.statemachinedemo.statemachine.enums.Event;
import com.example.statemachinedemo.statemachine.enums.State;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;


@Configuration
@EnableStateMachineFactory(name = "FaultStateMachineFactory")
public class StateMachineConfig extends StateMachineConfigurerAdapter<State, Event> {

    // 初始化状态
    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states) throws Exception {
        states
                .withStates()
                // 初始化状态机状态
                .initial(State.CREATE)
                // 指定状态机的所有状态
                .states(EnumSet.allOf(State.class));
    }


    // 配置状态之间的流转关系
    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitions) throws Exception {
        transitions
                .withExternal()
                .source(State.CREATE).target(State.CREATING).event(Event.START)
                .and()
                .withExternal()
                .source(State.CREATING).target(State.CREATE_SUCCESS).event(Event.CREATE)
                .and()
                .withExternal()
                .source(State.CREATE_SUCCESS).target(State.CREATING).event(Event.MODIFY)
                .and()
                .withExternal()
                .source(State.CREATING).target(State.CREATE_FAIL).event(Event.CREATE_ERROR)
                .and()
                .withExternal()
                .source(State.DRILLING).target(State.CREATE_SUCCESS).event(Event.DRILL_COMPLETE)
                .and()
                .withExternal()
                .source(State.CREATING).target(State.DRILL_FAIL).event(Event.DRILL_ERROR)
                .and()
                .withExternal()
                .source(State.CREATING).target(State.DRILL_STOP).event(Event.STOP)
                .and()
                .withExternal()
                .source(State.CREATE_SUCCESS).target(State.DELETE).event(Event.DELETE1)
                .and()
                .withExternal()
                .source(State.DRILL_SUCCESS).target(State.DELETE).event(Event.DELETE2)
                .and()
                .withExternal()
                .source(State.DRILL_FAIL).target(State.DELETE).event(Event.DELETE3)
                .and()
                .withExternal()
                .source(State.DRILL_STOP).target(State.DELETE).event(Event.DELETE4)
                .and()
                .withExternal()
                .source(State.CREATE_FAIL).target(State.DELETE).event(Event.DELETE5);

    }

    @Bean("faultStateMachinePersister")
    public StateMachinePersister<State, Event, Fault>  stateEventStateMachinePersister() {
        return new DefaultStateMachinePersister<>(
                new StateMachinePersist<State, Event, Fault>() {
                    @Override
                    public void write(StateMachineContext<State, Event> stateMachineContext, Fault fault) throws Exception {
                        fault.setState(stateMachineContext.getState().getValue());
                    }

                    @Override
                    public StateMachineContext<State, Event> read(Fault fault) throws Exception {
                        return new DefaultStateMachineContext<>(State.fromValue(fault.getState()), null, null, null, null, "faultStateMachinePersister");
                    }
                }
        );
    }

}
