package com.test.nolan.statemachine.pay.action;

import com.test.nolan.statemachine.pay.bean.Constant;
import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.spi.action.FlowAction;
import com.test.nolan.statemachine.bank.service.BankMockService;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import com.test.nolan.statemachine.pay.condition.InitPayCondition;
import com.test.nolan.statemachine.pay.service.OrderPayService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午3:49
 * @email: nolan.zhun@gmail.com
 * @description: 初始化支付流, 并存入db
 */
@Component
public class InitPayAction implements FlowAction<PayFlowContext> {
    @Resource
    private OrderPayService orderPayService;
    @Resource
    private BankMockService bankMockService;
    @Resource
    private InitPayCondition initPayCondition;

    @Override
    public FlowTrait execute(PayFlowContext context) throws Exception {
        PayRecordBean payRecordBean = context.getFlow().getPayRecordBean();
        // 模拟http通知银行扣款
        if (payRecordBean.getPayPrice() > 0) {
            String transactionId = bankMockService.mockTransfer(Constant.userAccountId, Constant.hotelAccountId, payRecordBean.getPayPrice());
            if ("".equals(transactionId)) {
                throw new RuntimeException("银行支付前校验失败");
            }
            payRecordBean.setTransactionId(transactionId);
        } else if (payRecordBean.getGuaranteePrice() > 0) {
            // 模拟http通知银行担保
            String transactionId = bankMockService.mockGuarantee(Constant.userAccountId, Constant.hotelAccountId, payRecordBean.getGuaranteePrice());
            if ("".equals(transactionId)) {
                throw new RuntimeException("银行担保前校验失败");
            }
            payRecordBean.setTransactionId(transactionId);
        }
        // 存储支付流
        orderPayService.saveFlow(context.getFlow(), orderPayService.buildOperationLog(context));
        return context.getFlow();
    }

    @Override
    public String getLabel() {
        return "订单去支付";
    }
}
