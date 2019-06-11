package com.test.nolan.statemachine.pay.action;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午5:07
 * @email: nolan.zhun@gmail.com
 * @description: 担保扣款, 用户预定但未按时入住, 通知银行进行扣款
 */
@Component
public class GuaranteeConfirmAction implements FlowAction<PayFlowContext> {
    @Override
    public FlowTrait execute(PayFlowContext context) throws Exception {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
