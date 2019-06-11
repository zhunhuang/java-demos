package com.test.nolan.statemachine.pay.action;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import com.test.nolan.statemachine.common.service.MQMockService;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import com.test.nolan.statemachine.pay.enums.PayStatus;
import com.test.nolan.statemachine.pay.service.OrderPayService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午4:19
 * @email: nolan.zhun@gmail.com
 * @description: 支付成功, 短信通知用户, 更新db
 */
@Component
public class PaySuccessAction implements FlowAction<PayFlowContext> {

    @Resource
    private OrderPayService orderPayService;
    @Resource
    private MQMockService mqMockService;

    @Override
    public FlowTrait execute(PayFlowContext context) throws Exception {
        PayRecordBean payRecordBean = context.getFlow().getPayRecordBean();
        // 发送支付消息
        mqMockService.mockPayStatusChangeMessage(payRecordBean.getOrderNo(), PayStatus.PAY_SUCCESS);
        return context.getFlow();
    }

    @Override
    public String getLabel() {
        return "[支付成功]";
    }
}
