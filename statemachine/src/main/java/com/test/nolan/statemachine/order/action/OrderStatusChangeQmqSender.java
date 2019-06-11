package com.test.nolan.statemachine.order.action;

import com.test.statemachine.spi.action.FlowPlugin;
import com.test.statemachine.spi.context.BaseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class OrderStatusChangeQmqSender implements FlowPlugin {
    private static final Logger logger = LoggerFactory.getLogger(OrderStatusChangeQmqSender.class);

    @Override
    public void beforeChangeState(BaseContext context) {
        logger.info("即将执行状态变更流程....., 当前状态:" + context.getFlow().currentState());
    }

    @Override
    public void afterChangeState(BaseContext context) {
        logger.info("状态变更流程执行结束....., 当前状态:" + context.getFlow().currentState());
    }
}
