package com.test.nolan.statemachine.pay.condition;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.exception.FlowConditionCheckException;
import com.test.statemachine.spi.action.FlowCondition;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:24
 * @email: nolan.zhun@gmail.com
 * @description: 查看担保账户是否能够担保
 */
@Component
public class GuranteeSuccessConditon implements FlowCondition<PayFlowContext> {
    @Override
    public void check(PayFlowContext context) throws FlowConditionCheckException {

    }

    @Override
    public String getLabel() {
        return null;
    }
}
