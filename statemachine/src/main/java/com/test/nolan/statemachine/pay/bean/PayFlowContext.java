package com.test.nolan.statemachine.pay.bean;

import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.spi.context.BaseContext;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午8:46
 * @email: nolan.zhun@gmail.com
 * @description: 订单流执行的上下文
 */
public class PayFlowContext extends BaseContext<PayFlowTrait> {

    public PayFlowContext(String flowId, ExecutorInfo executorInfo, PayFlowTrait payFlowTrait) {
        super(flowId, executorInfo);
        this.setFlow(payFlowTrait);
    }
}
