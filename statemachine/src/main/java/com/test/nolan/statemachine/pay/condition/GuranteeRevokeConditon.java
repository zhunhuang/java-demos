package com.test.nolan.statemachine.pay.condition;

import com.test.statemachine.api.exception.FlowConditionCheckException;
import com.test.statemachine.spi.action.FlowCondition;
import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:24
 * @email: nolan.zhun@gmail.com
 * @description: 查看能否撤销担保, 检查用户是否已经入住并离店
 */
@Component
public class GuranteeRevokeConditon implements FlowCondition<PayFlowContext> {
    @Override
    public void check(PayFlowContext context) throws FlowConditionCheckException {

    }

    @Override
    public String getLabel() {
        return null;
    }
}
