package com.nolan.test.statemachine;

import com.nolan.test.statemachine.event.Events;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.EnumSet;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/28
 */
@Configuration
public class StateMachineConfig {

    @Bean
    public StateMachine<States, Events> buildMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(States.INIT)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.INIT).target(States.PAY_COMPLETE)
                .event(Events.CREATE_ORDER)
                .and()
                .withExternal()
                .source(States.PAY_COMPLETE).target(States.REFUND_COMPLETE)
                .event(Events.REFUND_REQUEST);

        return builder.build();
    }
}
