package com.test.nolan.statemachine.pay.action;

import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:21
 * @email: nolan.zhun@gmail.com
 * @description:  担保成功, 通知酒店扣库存, 更新db
 */
@Component
public class GuaranteeSuccessAction implements FlowAction<PayFlowContext> {
    @Override
    public FlowTrait execute(PayFlowContext context) throws Exception {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
