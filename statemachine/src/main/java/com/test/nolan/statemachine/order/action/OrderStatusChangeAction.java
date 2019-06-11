package com.test.nolan.statemachine.order.action;

import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.core.model.OperationLog;
import com.test.statemachine.spi.action.FlowAction;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import com.test.nolan.statemachine.order.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午9:49
 * @email: nolan.zhun@gmail.com
 * @description: 订单状态变更动作
 */
@Component
public class OrderStatusChangeAction implements FlowAction<OrderFlowContext> {
    @Resource
    private OrderService orderService;

    @Override
    public FlowTrait execute(OrderFlowContext orderRefundFlowContext) throws Exception {
        OperationLog operationLog = new OperationLog();
        operationLog.setExecutorInfo(orderRefundFlowContext.getExecutorInfo());
        operationLog.setContent(orderRefundFlowContext.getLogComment());
        orderService.updateFlow(orderRefundFlowContext.getFlow(), operationLog);
        return orderRefundFlowContext.getFlow();
    }

    @Override
    public String getLabel() {
        return "订单状态变更";
    }
}
