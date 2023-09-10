package com.example.lmxdemo.statemachine;


import com.example.lmxdemo.enums.DrillEventEnum;
import com.example.lmxdemo.enums.DrillStateEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;


@Configuration
@EnableStateMachine
public class DrillStateMachineConfig extends StateMachineConfigurerAdapter<DrillStateEnum, DrillEventEnum> {

    // 初始化状态
    @Override
    public void configure(StateMachineStateConfigurer<DrillStateEnum, DrillEventEnum> states) throws Exception {
        states
                .withStates()
                // 初始化状态机状态
                .initial(DrillStateEnum.READY)
                // 指定状态机的所有状态
                .states(EnumSet.allOf(DrillStateEnum.class));
    }


    // 配置状态之间的流转关系
    @Override
    public void configure(StateMachineTransitionConfigurer<DrillStateEnum, DrillEventEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(DrillStateEnum.READY).target(DrillStateEnum.DRILLING).event(DrillEventEnum.START)
                .and()
                .withExternal()
                .source(DrillStateEnum.DRILLING).target(DrillStateEnum.PAUSE).event(DrillEventEnum.PAUSE)
                .and()
                .withExternal()
                .source(DrillStateEnum.PAUSE).target(DrillStateEnum.DRILLING).event(DrillEventEnum.GOON)
                .and()
                .withExternal()
                .source(DrillStateEnum.DRILLING).target(DrillStateEnum.SUCCESS).event(DrillEventEnum.COMPLETE)
                .and()
                .withExternal()
                .source(DrillStateEnum.DRILLING).target(DrillStateEnum.FAIL).event(DrillEventEnum.DRILLERROR)
                .and()
                .withExternal()
                .source(DrillStateEnum.SUCCESS).target(DrillStateEnum.READY).event(DrillEventEnum.RESTART)
                .and()
                .withExternal()
                .source(DrillStateEnum.READY).target(DrillStateEnum.FAIL).event(DrillEventEnum.CONFERROR)
                .and()
                .withInternal()
                .source(DrillStateEnum.READY).event(DrillEventEnum.MODIFYCONF)
                .and()
                .withInternal()
                .source(DrillStateEnum.DRILLING).event(DrillEventEnum.FORWARD)
                .and()
                .withInternal()
                .source(DrillStateEnum.DRILLING).event(DrillEventEnum.BACKWARD);
    }
}
