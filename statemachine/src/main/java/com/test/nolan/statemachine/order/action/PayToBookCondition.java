package com.test.nolan.statemachine.order.action;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.api.exception.FlowConditionCheckException;
import com.test.statemachine.api.recorder.ActionExecutedRecord;
import com.test.statemachine.spi.action.FlowCondition;
import com.test.nolan.statemachine.order.bean.OrderFlowContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PayToBookCondition implements FlowCondition<OrderFlowContext> {
    @Override
    public void check(OrderFlowContext context) throws FlowConditionCheckException {
        Map<Long, ActionExecutedRecord> recorderMap = context.getFlow().getActionRecorderStoreData().getRecorderMap();
        for (Map.Entry entry : recorderMap.entrySet()) {
            System.out.println("key is : " +entry.getKey());
            System.out.println("value is : " + entry.getValue());
        }
        System.out.println("=========================");
        System.out.println("校验从支付到生单状态扭转参数...");
        if (!context.getExecutorInfo().getExecutorRole().equals(ExecutorInfo.ExecutorRole.SYSTEM)) {
            System.out.println("仅允许系统角色进行这项操作");
            throw new FlowConditionCheckException("仅允许[系统角色]进行这项操作");
        }
    }

    @Override
    public String getLabel() {
        return "[执行角色校验]";
    }
}
