package com.test.nolan.statemachine.order.condition;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.api.exception.FlowConditionCheckException;
import com.test.statemachine.api.recorder.ActionExecutedRecord;
import com.test.statemachine.spi.action.FlowCondition;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import org.springframework.stereotype.Component;

@Component
public class InitOrderCondition implements FlowCondition<OrderFlowContext> {

    @Override
    public void check(OrderFlowContext context) throws FlowConditionCheckException {
        if (!context.getExecutorInfo().getExecutorRole().equals(ExecutorInfo.ExecutorRole.USER)) {
            System.out.println("仅允许用户角色进行这项操作");
            throw new FlowConditionCheckException("仅允许[用户角色]进行这项操作");
        }
        if (context.getFlow().getHotelOrderBean() == null) {
            throw new FlowConditionCheckException("orderBean 不允许为空");
        }
    }

    @Override
    public String getLabel() {
        return "[初始化订单条件]";
    }
}
