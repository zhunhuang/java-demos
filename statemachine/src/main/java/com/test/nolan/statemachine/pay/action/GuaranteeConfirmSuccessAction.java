package com.test.nolan.statemachine.pay.action;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午5:07
 * @email: nolan.zhun@gmail.com
 * @description: 担保扣款成功, 通知用户由于未按时入住, 扣除担保金额
 */
@Component
public class GuaranteeConfirmSuccessAction implements FlowAction<PayFlowContext> {
    @Override
    public FlowTrait execute(PayFlowContext context) throws Exception {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
