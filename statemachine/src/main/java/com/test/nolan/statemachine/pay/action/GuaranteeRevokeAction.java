package com.test.nolan.statemachine.pay.action;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:21
 * @email: nolan.zhun@gmail.com
 * @description:  撤销担保, 通知银行撤销担保账户的钱.
 */
@Component
public class GuaranteeRevokeAction implements FlowAction<PayFlowContext> {
    @Override
    public FlowTrait execute(PayFlowContext context) throws Exception {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
