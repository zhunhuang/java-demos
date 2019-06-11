package com.test.nolan.statemachine.pay.action;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import com.test.statemachine.spi.action.FlowActionSkip;
import com.test.nolan.statemachine.pay.enums.PayStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午5:05
 * @email: nolan.zhun@gmail.com
 * @description: 资金解冻->解冻成功, 更新db
 */
@Component
public class UnfreezeSuccessAction implements FlowAction<PayFlowContext>, FlowActionSkip<PayFlowContext>{
    private static final Logger logger = LoggerFactory.getLogger(UnfreezeSuccessAction.class);
    @Override
    public FlowTrait execute(PayFlowContext context) throws Exception {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public boolean shouldSkip(PayFlowContext context) {
        if (context.getFlow().currentState().equals(PayStatus.UNFREEZE.name())) {
        logger.info("解冻中->解冻成功, 该action应该跳过");
            return true;
        }
        return false;
    }
}
