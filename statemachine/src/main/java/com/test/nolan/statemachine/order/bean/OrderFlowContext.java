package com.test.nolan.statemachine.order.bean;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.spi.context.BaseContext;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午7:40
 * @email: nolan.zhun@gmail.com
 * @description: 用于订单执行的上下文
 */
public class OrderFlowContext extends BaseContext<OrderFlowTrait> {
    public OrderFlowContext(String flowId, ExecutorInfo executorInfo, OrderFlowTrait orderFlowTrait) {
        super(flowId, executorInfo);
        this.setFlow(orderFlowTrait);
    }
}
